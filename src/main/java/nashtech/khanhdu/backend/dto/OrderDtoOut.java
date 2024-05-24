package nashtech.khanhdu.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDtoOut {

    private Long id;
    private int quantity;
    private String productName;
}
