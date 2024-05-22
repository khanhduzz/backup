package nashtech.khanhdu.backend.services;

import nashtech.khanhdu.backend.dto.SignUpDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetails signUp (SignUpDto data);

    UserDetails loadUserByUsername (String username);
}
