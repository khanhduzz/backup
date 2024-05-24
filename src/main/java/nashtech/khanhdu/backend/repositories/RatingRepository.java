package nashtech.khanhdu.backend.repositories;

import nashtech.khanhdu.backend.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT o FROM Rating o JOIN o.userRating u JOIN o.productRating p " +
            "WHERE u.id = :userId AND p.id = :productId")
    Rating findByUserNameAndProductName(@Param("userId") Long userId, @Param("productId") Long productId);
}
