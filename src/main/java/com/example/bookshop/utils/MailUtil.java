package com.example.bookshop.utils;

import com.example.bookshop.controller.PasswordController;
import javafx.scene.text.Font;

import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.*;

public class MailUtil {

    private static int confirmationNumber;

    public static void sendMail(String receipt) throws Exception{

        System.out.println("Mail Send Preparing.");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccount = "ireshdilshan46@gmail.com";
        String password = "vhsv nryj agmz lpqc";

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccount, password);
            }
        });

        Message message = prepareMessage(session,myAccount,receipt);
        Transport.send(message);
        System.out.println("Message Sent Successfully.");
    }

    private static Message prepareMessage(Session session, String myAccount, String receipt) throws Exception {

        boolean isGenerate = true;
        int number = 0;
        Random random = new Random();
        while (isGenerate) {
            number = random.nextInt(1000000);
            if (number > 100000) {
                isGenerate = false;
            } else {
                isGenerate = true;
            }
        } confirmationNumber = number; // Store the generated confirmation number

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccount));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receipt));
        message.setSubject("Welcme to Novel Nook. ");
        message.setText("Your Confirmation Number is \n\n\t\t\t\t" + number);
        System.out.println(number);
        return message;
    }
    public static int getConfirmationNumber() { return confirmationNumber; }
}
