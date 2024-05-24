package nashtech.khanhdu.backend.services;

import nashtech.khanhdu.backend.dto.RatingDto;
import nashtech.khanhdu.backend.entities.Rating;
import org.springframework.http.ResponseEntity;

public interface RatingService {

    ResponseEntity<Rating> createOrUpdateRating(RatingDto dto);

    ResponseEntity<Rating> deleteRating(Rating rating);
}
