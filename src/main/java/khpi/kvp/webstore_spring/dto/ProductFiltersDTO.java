package khpi.kvp.webstore_spring.dto;

import lombok.Data;

@Data
public class ProductFiltersDTO {
    private int categoryId;
    private double minPrice;
    private double maxPrice;
}
