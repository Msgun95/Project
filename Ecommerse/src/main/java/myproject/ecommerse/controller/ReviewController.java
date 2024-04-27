package myproject.ecommerse.controller;

import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.ReviewDTO;
import myproject.ecommerse.model.Review;
import myproject.ecommerse.service.imp.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping("/customer/{customerId}/item/{itemId}")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO review,
                                                  @PathVariable int customerId,
                                                  @PathVariable int itemId){
        ReviewDTO addreview = reviewService.addReview(review, customerId, itemId);
        return ResponseEntity.ok(addreview);
    }
//    @PostMapping("")
//    public ResponseEntity<ReviewDTO> createReview2(@RequestBody ReviewDTO review){
//        ReviewDTO addreview = reviewService.addReview(review);
//        return ResponseEntity.ok(addreview);
//    }
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> retrieveAllReview (){
        return ResponseEntity.ok(reviewService.getAllReviews());

    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> retrieveReviewById(@PathVariable int id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @DeleteMapping("/{id}")
    public void geleteReview(@PathVariable int id){
        reviewService.deleteReview(id);
    }


}
