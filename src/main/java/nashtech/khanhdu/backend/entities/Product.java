package nashtech.khanhdu.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;
    private String name;
    private double price;
    private String description;
    private int featured;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "CATEGORIES_PRODUCTS",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "productRating", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    Set<Order> orders = new HashSet<>();
}
