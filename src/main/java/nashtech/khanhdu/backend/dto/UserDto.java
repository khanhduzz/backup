package nashtech.khanhdu.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
