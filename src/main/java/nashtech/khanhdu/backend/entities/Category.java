package nashtech.khanhdu.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @UniqueElements
    private String name;
    private String description;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    Set<Product> products;
}
