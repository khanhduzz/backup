package nashtech.khanhdu.backend.dto;

import jakarta.validation.constraints.NotNull;

public record OrderDto (@NotNull Long userId, @NotNull Long productId, @NotNull Integer quantity){
}
