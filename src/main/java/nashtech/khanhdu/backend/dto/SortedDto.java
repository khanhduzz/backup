package nashtech.khanhdu.backend.dto;

import jakarta.validation.constraints.NotNull;

public record SortedDto(Integer page, Integer number, @NotNull String sortedBy, Integer direction) {
}
