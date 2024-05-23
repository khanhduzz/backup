package nashtech.khanhdu.backend.services;

import nashtech.khanhdu.backend.dto.SignUpDto;
import nashtech.khanhdu.backend.dto.UserDto;
import nashtech.khanhdu.backend.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDetails signUp (SignUpDto data);

    UserDetails loadUserByUsername (String username);

    ResponseEntity<User> createUser(UserDto dto);

    ResponseEntity<User> updateUser(Long id, UserDto dto);

    ResponseEntity<String> deleteUser(Long id);

    List<User> getAllUsers();
}
