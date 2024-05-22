package nashtech.khanhdu.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "ROLES")
public class Role {

    @Id
    @Column(name = "ROLE_NAME")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    Set<User> users;
}
