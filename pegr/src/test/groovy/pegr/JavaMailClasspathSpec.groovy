package pegr

import spock.lang.Specification

/**
 * Guards the JavaMail classpath arrangement that feature/retire-javax-mail left behind.
 *
 * The vendored libs/javax.mail.jar (1.5.6) used to win the classpath and supply both the
 * javax.mail API and the com.sun.mail implementation from a single file. It is gone, and
 * the two halves now come from separate jars resolved transitively through the mail
 * plugin: the API from javax.mail-api, the implementation and its provider registry from
 * com.sun.mail:javax.mail.
 *
 * That split is the fragile part. A dependency change that drops or shadows the
 * implementation half still compiles, because every javax.mail type the API declares is
 * still on the classpath -- it only fails when someone actually asks for a Transport,
 * i.e. when a user requests a password reset. These checks turn that into a build failure.
 *
 * No network access: getTransport() resolves a provider and constructs the Transport, it
 * does not connect.
 */
class JavaMailClasspathSpec extends Specification {

    private static final Properties SMTP_PROPS = ['mail.smtp.host': 'localhost'] as Properties

    void "the vendored javax.mail.jar is not back on the classpath"() {
        given: "every jar that supplies the javax.mail API"
        def sources = getClass().classLoader.getResources("javax/mail/Session.class")*.toString()

        expect: "none of them is the retired vendored jar, which shadowed the plugin's newer copy"
        !sources.isEmpty()
        sources.every { !it.contains("/javax.mail.jar!") }
    }

    void "the JavaMail implementation is present, not just the API"() {
        when: "loading a class that only the implementation jar carries"
        Class.forName("com.sun.mail.smtp.SMTPTransport")

        then: "the api jar alone would compile but fail here"
        notThrown(ClassNotFoundException)
    }

    void "the provider registry is reachable"() {
        given: "Session reads this resource to discover transports; the api jar does not carry it"
        def registries = getClass().classLoader.getResources("META-INF/javamail.default.providers")

        expect:
        registries.hasMoreElements()
    }

    void "an SMTP transport can be resolved"() {
        given:
        def session = javax.mail.Session.getInstance(SMTP_PROPS)

        when: "asking for the provider the password-reset and account-creation mails use"
        def transport = session.getTransport("smtp")

        then: "a broken split across the api and implementation jars surfaces here"
        notThrown(javax.mail.NoSuchProviderException)
        transport.class.name == "com.sun.mail.smtp.SMTPTransport"
    }
}
