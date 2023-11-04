package khpi.kvp.webstore_spring.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int categoryId;
    private double price;
    private double priceOpt;
    private String description;
    private String imageName;
    private String article;
}
