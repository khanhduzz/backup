package nashtech.khanhdu.backend.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
