package com.example.angeldex.util;

import com.example.angeldex.model.entities.UserEntity;
import org.springframework.stereotype.Component;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class JavaMailSender {

    /**
     * Send the email via SMTP using TLS and SSL
     */
    public static int sendEmailChangePasswordRequest(UserEntity forgottenUser) {
        int randNum = ThreadLocalRandom.current().nextInt(100000, 1000000);

        // Create all the needed properties
        Properties connectionProperties = new Properties();
        // SMTP host
        connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
        // Is authentication enabled
        connectionProperties.put("mail.smtp.auth", "true");
        // Is TLS enabled
        connectionProperties.put("mail.smtp.starttls.enable", "true");
        // SSL Port
        connectionProperties.put("mail.smtp.socketFactory.port", "465");
        // SSL Socket Factory class
        connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // SMTP port, the same as SSL port :)
        connectionProperties.put("mail.smtp.port", "465");


        // Create the session
        Session session = Session.getDefaultInstance(connectionProperties,
                new javax.mail.Authenticator() {	// Define the authenticator
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("officialangeldex@gmail.com","cvuv cfed djwp wjep");
                    }
                });


        // Create and send the message
        try {
            // Create the message
            Message message = new MimeMessage(session);
            // Set sender
            message.setFrom(new InternetAddress("officialangeldex@gmail.com"));
            // Set the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(forgottenUser.getEmail()));
            // Set message subject
            message.setSubject("AngelDex \n Confirmation number");
            // Set message text
            message.setText("Hello from our team at AngelDex,\n    Your confirmation code is: " + randNum);

            // Send the message
            Transport.send(message);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return randNum;

    }
    public static void sendEmailUponEmailRegistration(UserEntity user) {
        // Create all the needed properties
        Properties connectionProperties = new Properties();
        // SMTP host
        connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
        // Is authentication enabled
        connectionProperties.put("mail.smtp.auth", "true");
        // Is TLS enabled
        connectionProperties.put("mail.smtp.starttls.enable", "true");
        // SSL Port
        connectionProperties.put("mail.smtp.socketFactory.port", "465");
        // SSL Socket Factory class
        connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // SMTP port, the same as SSL port :)
        connectionProperties.put("mail.smtp.port", "465");

        // Create the session
        Session session = Session.getDefaultInstance(connectionProperties,
                new javax.mail.Authenticator() {	// Define the authenticator
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("officialangeldex@gmail.com","cvuv cfed djwp wjep");
                    }
                });


        // Create and send the message
        try {
            // Create the message
            Message message = new MimeMessage(session);
            // Set sender
            message.setFrom(new InternetAddress("officialangeldex@gmail.com"));
            // Set the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            // Set message subject
            message.setSubject("AngelDex \n Welcome to our Web app!");
            // Set message text
            message.setText("Hello from our team at AngelDex,\n   Welcome to our " +
                    "web application. We are very happy to have you part of our " +
                    "growing community. What you can expect is innovation and " +
                    "a lot of fun releases.");

            // Send the message
            Transport.send(message);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void sendEmailUponEmailRegistration(String email) {
        Properties connectionProperties = new Properties();
        connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
        connectionProperties.put("mail.smtp.auth", "true");
        connectionProperties.put("mail.smtp.starttls.enable", "true");
        connectionProperties.put("mail.smtp.socketFactory.port", "465");
        connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        connectionProperties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(connectionProperties,
                new javax.mail.Authenticator() {	// Define the authenticator
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("officialangeldex@gmail.com","cvuv cfed djwp wjep");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("officialangeldex@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("AngelDex \n Welcome to our Web app!");
            message.setText("Hello from our team at AngelDex,\n   Welcome to our " +
                    "web application. We are very happy to have you part of our " +
                    "growing community. What you can expect is innovation and " +
                    "a lot of fun releases.");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
