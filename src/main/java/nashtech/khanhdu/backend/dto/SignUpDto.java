package nashtech.khanhdu.backend.dto;

import java.util.Set;

public record SignUpDto(String username, String password, Set<String> roles) {
}
