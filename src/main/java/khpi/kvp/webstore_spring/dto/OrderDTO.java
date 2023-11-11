package khpi.kvp.webstore_spring.dto;

import khpi.kvp.webstore_spring.models.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<OrderItem> orderItems;
    private String address1;
    private String address2;
    private int postCode;
    private String city;
    private String phone;
    public String email;
    public String description;
}
