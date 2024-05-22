package nashtech.khanhdu.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class ErrorResponse {

    Integer code;
    String message;
    Object errors;
}
