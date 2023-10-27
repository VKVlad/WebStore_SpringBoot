package khpi.kvp.webstore_spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "article", unique = true)
    private String article;

    @Column(name = "nazva")
    private String nazva;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "priceOpt", precision = 10, scale = 2)
    private BigDecimal priceOpt;

    @Column(name = "image_path")
    private String imagePath;

    public Good(String nazva, String category, String article, BigDecimal price, BigDecimal priceOpt, String imagePath) {
        this.nazva = nazva;
        this.category = category;
        this.article = article;
        this.price = price;
        this.priceOpt = priceOpt;
        this.imagePath = imagePath;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Good)) return false;
        final Good other = (Good) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        final Object this$article = this.getArticle();
        final Object other$article = other.getArticle();
        if (this$article == null ? other$article != null : !this$article.equals(other$article)) return false;
        final Object this$nazva = this.getNazva();
        final Object other$nazva = other.getNazva();
        if (this$nazva == null ? other$nazva != null : !this$nazva.equals(other$nazva)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$priceOpt = this.getPriceOpt();
        final Object other$priceOpt = other.getPriceOpt();
        if (this$priceOpt == null ? other$priceOpt != null : !this$priceOpt.equals(other$priceOpt)) return false;
        final Object this$imagePath = this.getImagePath();
        final Object other$imagePath = other.getImagePath();
        if (this$imagePath == null ? other$imagePath != null : !this$imagePath.equals(other$imagePath)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Good;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $article = this.getArticle();
        result = result * PRIME + ($article == null ? 43 : $article.hashCode());
        final Object $nazva = this.getNazva();
        result = result * PRIME + ($nazva == null ? 43 : $nazva.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $priceOpt = this.getPriceOpt();
        result = result * PRIME + ($priceOpt == null ? 43 : $priceOpt.hashCode());
        final Object $imagePath = this.getImagePath();
        result = result * PRIME + ($imagePath == null ? 43 : $imagePath.hashCode());
        return result;
    }

    public String toString() {
        return "Good(id=" + this.getId() + ", category=" + this.getCategory()
                + ", article=" + this.getArticle() + ", nazva=" + this.getNazva()
                + ", price=" + this.getPrice() + ", priceOpt=" + this.getPriceOpt()
                + ", imagePath=" + this.getImagePath() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public String getCategory() {
        return this.category;
    }

    public String getArticle() {
        return this.article;
    }

    public String getNazva() {
        return this.nazva;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getPriceOpt() {
        return this.priceOpt;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setNazva(String nazva) {
        this.nazva = nazva;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPriceOpt(BigDecimal priceOpt) {
        this.priceOpt = priceOpt;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}