package nashtech.khanhdu.backend.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
    }
}
