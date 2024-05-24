package nashtech.khanhdu.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RatingDto(@NotNull Long userId, @NotNull Long productId, @NotNull @Min(0) Integer rate) {
}
