package khpi.kvp.webstore_spring.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import khpi.kvp.webstore_spring.models.Order;
import khpi.kvp.webstore_spring.models.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public void sendEmailWithAttachment(String toEmail, List<OrderItem> orderItems, LocalDate targetDate)  {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject("Orders from ITech");
            File orderFile = createOrdersFile(targetDate, orderItems);
            if (orderFile != null) {
                mimeMessageHelper.addAttachment("OrderDetails.txt", orderFile);
                mimeMessageHelper.setText("blabla");
                javaMailSender.send(mimeMessage);
                orderFile.delete();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private File createOrdersFile(LocalDate targetDate, List<OrderItem> orderItems) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");
            String formattedDate = targetDate.format(formatter);

            File ordersFile = new File("Orders_" + formattedDate + ".txt");

            try (FileWriter fileWriter = new FileWriter(ordersFile)) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrders().getDateTime().toLocalDate().equals(targetDate)) {
                        fileWriter.write("Order Details:\n");
                        fileWriter.write("Order ID: " + orderItem.getOrders().getId() + "\n");
                        fileWriter.write("Date: " + orderItem.getOrders().getDateTime() + "\n");
                        fileWriter.write("Customer: " + orderItem.getOrders().getFirstName() + " " + orderItem.getOrders().getLastName() + "\n");
                        fileWriter.write("Product: " + orderItem.getProduct().getName() + "Article: " + orderItem.getProduct().getArticle() + " Quantity: " + orderItem.getQuantity() + "\n");
                        fileWriter.write("Address1: " + orderItem.getOrders().getAddress1() + " Address1: " + orderItem.getOrders().getAddress2() + "\n");
                        fileWriter.write("PostCode: " + orderItem.getOrders().getPostCode() + "\n");
                        fileWriter.write("Phone: " + orderItem.getOrders().getPhone() + "\n");
                        fileWriter.write("Email: " + orderItem.getOrders().getEmail() + "\n");
                        fileWriter.write("Description: " + orderItem.getOrders().getDescription() + "\n");
                        fileWriter.write("\n");
                    }
                }
            }

            return ordersFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
