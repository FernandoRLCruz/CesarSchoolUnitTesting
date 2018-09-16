package school.cesar.unit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmailTest {


    @Mock
    EmailService emailService;

    private Email email;
    private EmailClient emailClient;
    private EmailAccount emailAccount;
    private EmailAccountBuilder emailAccountBuilder;
    private EmailAccountBuilder emailAccountBuilderPasswordExpiration;
    private EmailAccountBuilder emailAccountBuilderAccountInvalid;
    private EmailAccountBuilder emailAccountBuilderPasswordInvalid;
    private EmailBuilder emailbuilder;
    private EmailBuilder emailbuilderToInvalid;
    private EmailBuilder emailbuilderCcInvalid;
    private EmailBuilder emailbuilderBccInvalid;
    private EmailBuilder emailbuilderFromInvalid;

    private static final String MAURICIO_VALID_EMAIL = "mauricio.chaves@cesar.school";
    private static final String FERNANDO_VALID_EMAIL = "ferando.cruz@cesar.school";
    private static final String RENATO_VALID_EMAIL = "renato.araujo@cesar.school";
    private static final String INVALID_EMAIL = "mauricio.chavescesar.school";
    private static final String INVALID_USER_EMAIL = "mauricio'chaves@cesar.school";

    private static final String INVALID_DOMAIN_EMAIL = "mauricio.chaves@cesar'school";
    private static final String VALID_DOMAIN_EMAIL = "@cesar.school";
    private static final String VALID_USER_EMAIL = "mauricio.chaves";
    private static final String PASSWORD = "123456mj";
    private static final String INVALID_PASSWORD = "1234";
    private static final String FIRST_POINT_DOMAIN_EMAIL = "mauricio.chaves@.cesarschool";
    private static final String LAST_POINT_DOMAIN_EMAIL = "mauricio.chaves@cesarschool.";
    private static final String DOUBLE_POINT_DOMAIN_EMAIL = "mauricio.chaves@cesar..school";

    private EmailAccount emailaccount;
    private EmailAccountBuilder emailaccountbuilderPassWordNoExpiration;
    private static final String DOMAIN_EMAIL = "cesar.school";
    private static final String USER_EMAIL = "mauricio.chaves";









    @BeforeEach
    public void setUp() {

            emailClient = new EmailClient();
            List to_valid = new ArrayList();
            List cc_valid = new ArrayList();
            List bcc_valid = new ArrayList();
            List to_invalid = new ArrayList();
            List cc_invalid = new ArrayList();
            List bcc_invalid = new ArrayList();


            //valid email
            to_valid.add(MAURICIO_VALID_EMAIL);
            cc_valid.add(RENATO_VALID_EMAIL);
            bcc_valid.add(FERNANDO_VALID_EMAIL);
            emailbuilder = new EmailBuilder().setBcc(bcc_valid)
                    .setCc(cc_valid)
                    .setTo(to_valid)
                    .setFrom(MAURICIO_VALID_EMAIL)
                    .setMessage("Ola Mundo")
                    .setSubject("VAGA-AUTOMACAO").setCreationDate(LocalDate.now());

            //emailbuilderToInvalid
            to_invalid.add(INVALID_EMAIL);
            cc_valid.add(RENATO_VALID_EMAIL);
            bcc_valid.add(FERNANDO_VALID_EMAIL);
            emailbuilderToInvalid = new EmailBuilder().setBcc(bcc_valid)
                    .setCc(cc_valid)
                    .setTo(to_invalid)
                    .setFrom(MAURICIO_VALID_EMAIL)
                    .setMessage("Ola Mundo")
                    .setSubject("VAGA-AUTOMACAO").setCreationDate(LocalDate.now());

            //emailbuilderCcInvalid
            to_valid.add(MAURICIO_VALID_EMAIL);
            cc_invalid.add(INVALID_EMAIL);
            bcc_valid.add(FERNANDO_VALID_EMAIL);
            emailbuilderCcInvalid = new EmailBuilder().setBcc(bcc_valid)
                    .setCc(cc_invalid)
                    .setTo(to_valid)
                    .setFrom(MAURICIO_VALID_EMAIL)
                    .setMessage("Ola Mundo")
                    .setSubject("VAGA-AUTOMACAO").setCreationDate(LocalDate.now());

            //emailbuilderBccInvalid
            to_valid.add(MAURICIO_VALID_EMAIL);
            cc_valid.add(FERNANDO_VALID_EMAIL);
            bcc_invalid.add(INVALID_EMAIL);
            emailbuilderBccInvalid = new EmailBuilder().setBcc(bcc_invalid)
                    .setCc(cc_valid)
                    .setTo(to_valid)
                    .setFrom(MAURICIO_VALID_EMAIL)
                    .setMessage("Ola Mundo")
                    .setSubject("VAGA-AUTOMACAO").setCreationDate(LocalDate.now());

            //emailbuilderFromInvalid
            to_valid.add(MAURICIO_VALID_EMAIL);
            cc_valid.add(FERNANDO_VALID_EMAIL);
            bcc_valid.add(RENATO_VALID_EMAIL);
            emailbuilderFromInvalid = new EmailBuilder().setBcc(bcc_valid)
                    .setCc(cc_valid)
                    .setTo(to_valid)
                    .setFrom(INVALID_EMAIL)
                    .setMessage("Ola Mundo")
                    .setSubject("VAGA-AUTOMACAO").setCreationDate(LocalDate.now());

            //emailAccountBuilder
            emailAccountBuilder = new EmailAccountBuilder()
                    .setDomain(VALID_DOMAIN_EMAIL)
                    .setUser(VALID_USER_EMAIL)
                    .setLastPasswordUpdate(LocalDate.now())
                    .setPassword(PASSWORD);

            //emailAccountBuilderAccountInvalid
            emailAccountBuilderAccountInvalid = new EmailAccountBuilder()
                    .setDomain(INVALID_DOMAIN_EMAIL)
                    .setUser(INVALID_USER_EMAIL)
                    .setLastPasswordUpdate(LocalDate.now())
                    .setPassword(PASSWORD);

            //emailAccountBuilderPasswordInvalid
            emailAccountBuilderPasswordInvalid = new EmailAccountBuilder()
                    .setDomain(VALID_DOMAIN_EMAIL)
                    .setUser(VALID_USER_EMAIL)
                    .setLastPasswordUpdate(LocalDate.now())
                    .setPassword(INVALID_PASSWORD);

            //emailAccountBuilderPasswordExpiration
            emailAccountBuilderPasswordExpiration = new EmailAccountBuilder()
                    .setDomain(VALID_DOMAIN_EMAIL)
                    .setUser(VALID_USER_EMAIL)
                    .setLastPasswordUpdate(LocalDate.now().minusDays(91))
                    .setPassword(PASSWORD);


        //emailAccountBuilderPasswordExpiration
        emailAccountBuilderPasswordExpiration = new EmailAccountBuilder()
                .setDomain(DOMAIN_EMAIL)
                .setUser(USER_EMAIL)
                .setLastPasswordUpdate(LocalDate.now().minusDays(91))
                .setPassword(PASSWORD);

        //emailaccountbuilderPassWordNoExpiration
        emailaccountbuilderPassWordNoExpiration = new EmailAccountBuilder()
                .setDomain(DOMAIN_EMAIL)
                .setLastPasswordUpdate(LocalDate.now())
                .setPassword(PASSWORD)
                .setUser(USER_EMAIL);

    }


    @Test
    void createAccountTest_AccountValid_True() {
        emailAccount = emailAccountBuilder.build();
        Assertions.assertTrue(emailClient.createAccount(emailAccount));
    }
    @Test
    void createAccountTest_AccountInvalid_False() {
        emailAccount = emailAccountBuilderAccountInvalid.build();
        Assertions.assertFalse(emailClient.createAccount(emailAccount));
    }
    @Test
    void createAccountTest_PasswordInvalid_False() {
        emailAccount = emailAccountBuilderPasswordInvalid.build();
        Assertions.assertFalse(emailClient.createAccount(emailAccount));
    }
    @Test
    void emailListTest_AccountValid_NoException() {
        emailAccount = emailAccountBuilder.build();
        email = emailbuilder.build();

        Collection<Email> mockEmail = new ArrayList<Email>();
        mockEmail.add(email);
        when(emailService.emailList(any(EmailAccount.class))).thenReturn(mockEmail);
        emailClient.setEmailService(emailService);

        Assertions.assertEquals(mockEmail,emailClient.emailList(emailAccount));
    }
    @Test
    void emailListTest_PasswordExpiration_Exception() {
        emailAccount = emailAccountBuilderPasswordExpiration.build();
        Assertions.assertThrows( RuntimeException.class, () -> {emailClient.emailList(emailAccount);});
    }
    @Test
    void emailListTest_PasswordInvalid_Exception() {
        emailAccount = emailAccountBuilderPasswordInvalid.build();
        Assertions.assertThrows( RuntimeException.class, () -> {emailClient.emailList(emailAccount);});
    }
    @Test
    void sendEmailTest_EmailValid_NoException() {
        email = emailbuilder.build();
        when(emailService.sendEmail(any(Email.class))).thenReturn(true);
        emailClient.setEmailService(emailService);
        Assertions.assertDoesNotThrow( () -> {emailClient.sendEmail(email);});
    }
    @Test
    void sendEmailTest_EmailInvalid_Exception() {
        email = emailbuilderToInvalid.build();
        when(emailService.sendEmail(any(Email.class))).thenReturn(false);
        emailClient.setEmailService(emailService);
        Assertions.assertThrows( RuntimeException.class, () -> {emailClient.sendEmail(email);});
    }
    @Test
    void isValidEmailTest_EmailValid_True() {
        email = emailbuilder.build();
        Assertions.assertTrue(emailClient.isValidEmail(email));
    }
    @Test
    void isValidEmailTest_EmailToInvalid_False() {
        email = emailbuilderToInvalid.build();
        Assertions.assertFalse(emailClient.isValidEmail(email));
    }
    @Test
    void isValidEmailTest_EmailCcInvalid_False() {
        email = emailbuilderCcInvalid.build();
        Assertions.assertFalse(emailClient.isValidEmail(email));
    }
    @Test
    void isValidEmailTest_EmailBccInvalid_False() {
        email = emailbuilderBccInvalid.build();
        Assertions.assertFalse(emailClient.isValidEmail(email));
    }
    @Test
    void isValidEmailTest_EmailFromInvalid_False() {
        email = emailbuilderFromInvalid.build();
        Assertions.assertFalse(emailClient.isValidEmail(email));
    }
    @Test
    void isValidAddressTest_ValidEmail_True() {
        Assertions.assertTrue(emailClient.isValidAddress(MAURICIO_VALID_EMAIL));
    }
    @Test
    void isValidAddressTest_InvalidEmail_False() {
        Assertions.assertFalse(emailClient.isValidAddress(INVALID_EMAIL));
    }

    @Test
    void isValidAddressTest_InvalidUser_False() {
        Assertions.assertFalse(emailClient.isValidAddress(INVALID_USER_EMAIL));
    }

    @Test
    void isValidAddressTest_FirstPointDomain_False() {
        Assertions.assertFalse(emailClient.isValidAddress(FIRST_POINT_DOMAIN_EMAIL));
    }
    @Test
    void isValidAddressTest_LastPointDomain_False() {
        Assertions.assertFalse(emailClient.isValidAddress(LAST_POINT_DOMAIN_EMAIL));
    }
    @Test
    void isValidAddressTest_DoublePointDomain_False() {
        Assertions.assertFalse(emailClient.isValidAddress(DOUBLE_POINT_DOMAIN_EMAIL));
    }
    @Test
    void isValidAddressTest_InvalidDomain_False() {
        Assertions.assertFalse(emailClient.isValidAddress(INVALID_DOMAIN_EMAIL));
    }

    @Test
    void verifyPasswordExpirationTest_ExpirationDate_True() {
        emailaccount = emailAccountBuilderPasswordExpiration.build();
        Assertions.assertTrue(emailaccount.verifyPasswordExpiration());
    }
    @Test
    void verifyPasswordExpirationTest_NotExpirationDate_False() {
        emailaccount = emailaccountbuilderPassWordNoExpiration.build();
        Assertions.assertFalse(emailaccount.verifyPasswordExpiration());
    }

    @AfterEach
    public void tearDown() {


    }


}
