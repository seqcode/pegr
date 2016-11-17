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

import com.google.api.services.gmail.GmailScopes
import com.google.api.services.gmail.model.*
import com.google.api.services.gmail.Gmail

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Arrays
import java.util.List

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
     * at ~/.credentials/gmail-java-quickstart
     */
    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS)

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
    
    def getLabels() {
        // Build a new authorized API client service.
        Gmail service = getGmailService()

        // Print the labels in the user's account.
        String user = "me"
        ListLabelsResponse listResponse =
            service.users().labels().list(user).execute()
        List<Label> labels = listResponse.getLabels()
        if (labels.size() == 0) {
            System.out.println("No labels found.")
        } else {
            System.out.println("Labels:")
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName())
            }
        }
    }
}
