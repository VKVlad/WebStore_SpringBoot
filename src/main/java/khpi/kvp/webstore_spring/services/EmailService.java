package khpi.kvp.webstore_spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String verificationCode) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("Your Verification code for ITech");
        simpleMailMessage.setText("Code: " + verificationCode);
        javaMailSender.send(simpleMailMessage);
    }
}
