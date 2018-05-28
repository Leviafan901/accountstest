package by.tut.accounttests.mailer;

import by.tut.accounttests.domain.UserAccount;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public  class JavaMailer {

	private final static String SENT_FOLDER_NAME = "Отправленные";
    public static boolean sendMail(UserAccount firstAccount, UserAccount secondAccount, Mail mail) {
    
        String addressee = secondAccount.getEmail();
        String mailer = firstAccount.getEmail();
        String mailerPassword = firstAccount.getPassword();
        String host = "smtp.yandex.ru";
        int port = 465;
    
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.imap.socketFactory.fallback","false");
        properties.put("mail.imap.socketFactory.port", 993);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailer, mailerPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailer));
            InternetAddress[] address = {new InternetAddress(addressee)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(mail.getMailTitle());
            message.setSentDate(new Date());
            message.setText(mail.getMailBody());
            Transport.send(message);
            copyIntoSent(mailer, mailerPassword, session, message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException("Can't send mail or append mail to folder!", e);
        }
    }
    
    private static void copyIntoSent(String login, String password, Session session, Message msg)
    		throws MessagingException {
        
        String imapHost = "imap.yandex.ru";
    	int imapPort = 993;
    	Store store = session.getStore("imap");
        store.connect(imapHost, imapPort, login, password);
 
        Folder folder = (Folder) store.getFolder(SENT_FOLDER_NAME);
        if (!folder.exists()) {
            folder.create(Folder.HOLDS_MESSAGES);
        }
        folder.open(Folder.READ_WRITE);
        folder.appendMessages(new Message[]{msg});
    }
}