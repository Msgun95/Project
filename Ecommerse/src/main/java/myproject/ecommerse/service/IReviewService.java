package myproject.ecommerse.service;

import myproject.ecommerse.dto.ReviewDTO;
import myproject.ecommerse.model.Review;

import java.util.List;

public interface IReviewService {
    ReviewDTO addReview(ReviewDTO reviewm, int customerId, int itemId);
    List<ReviewDTO> getAllReviews();
    ReviewDTO getReviewById(int id);
    void deleteReview(int id);
}
