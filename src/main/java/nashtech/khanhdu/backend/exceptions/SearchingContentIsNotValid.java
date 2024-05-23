package nashtech.khanhdu.backend.exceptions;

public class SearchingContentIsNotValid extends RuntimeException{
    public SearchingContentIsNotValid(){
    }

    public SearchingContentIsNotValid(String message) {
        super(message);
    }
}
