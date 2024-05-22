package nashtech.khanhdu.backend.exceptions;

public class UserExistException extends RuntimeException {
    public UserExistException(String mess) {
        super(mess);
    }
}
