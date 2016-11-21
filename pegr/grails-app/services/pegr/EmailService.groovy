package pegr

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64

import com.google.api.services.gmail.GmailScopes
import com.google.api.services.gmail.model.*
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.model.Message

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Arrays
import java.util.List

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class EmailService {
    def grailsApplication
    def utilityService
        
    /** Application name. */
    private static final String APPLICATION_NAME = "PEGR"

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance()

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/StoredCredential
     */
    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_SEND, GmailScopes.GMAIL_READONLY)

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    Credential authorize() {
        // Load client secrets.
        def clientId = grailsApplication.config.gmail.clientId
        def clientSecret = grailsApplication.config.gmail.clientSecret

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientId, clientSecret, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build()
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user")
        return credential
    }

    /**
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    Gmail getGmailService() {
        def DATA_STORE_DIR = utilityService.getFilesRoot()
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR)
        Credential credential = authorize()
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build()
    }
    
    /**
     * Create a MimeMessage using the parameters provided.
     *
     * @param to email address of the receiver
     * @param from email address of the sender, the mailbox account
     * @param subject subject of the email
     * @param bodyText body text of the email
     * @return the MimeMessage to be used to send email
     * @throws MessagingException
     */
    def createEmail(String to, String from, String subject, String bodyText) {
        Properties props = new Properties()
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session)

        email.setFrom(new InternetAddress(from))
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to))
        email.setSubject(subject)
        email.setText(bodyText)
        return email
    }
    
    /**
     * Create a message from an email.
     *
     * @param emailContent Email to be set to raw of message
     * @return a message containing a base64url encoded email
     * @throws IOException
     * @throws MessagingException
     */
    def createMessageWithEmail(MimeMessage emailContent) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream()
        emailContent.writeTo(buffer)
        byte[] bytes = buffer.toByteArray()
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes)
        Message message = new Message()
        message.setRaw(encodedEmail)
        return message
    }
    
    /**
     * Send an email from the user's mailbox to its recipient.
     *
     * @param service Authorized Gmail API instance.
     * @param userId User's email address. The special value "me"
     * can be used to indicate the authenticated user.
     * @param emailContent Email to be sent.
     * @return The sent message
     * @throws MessagingException
     * @throws IOException
     */
    def sendMessage(Gmail service, String userId, MimeMessage emailContent) {
        Message message = createMessageWithEmail(emailContent)
        message = service.users().messages().send(userId, message).execute()
        return message
    }
    
    def send(String to, String subject, String body) {
        // Build a new authorized API client service.
        Gmail service = getGmailService()
        String from = grailsApplication.config.gmail.from
        String user = "me"
        MimeMessage emailContent = createEmail(to, from, subject, body)
        sendMessage(service, user, emailContent)
    }
}
