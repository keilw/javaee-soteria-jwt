package id.swhp.javaee.jwt.business.book.entity;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Book {

    @JsonbProperty(value = "ISBN")
    private Long isbn;
    private String title;
    @JsonbProperty(nillable = true)
    private String summary;
    private Double price;

    public Book() {
    }

    public Book(Long isbn, String title, Double price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn + ", title=" + title + '}';
    }
}
