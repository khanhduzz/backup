package nashtech.khanhdu.backend.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record SignUpDto(String username, String password, Set<String> roles, @NotNull String email) {
}
