package nashtech.khanhdu.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JwtToken(@JsonProperty("access_token") String accessToken) {
}
