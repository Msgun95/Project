package myproject.ecommerse.service.imp;

import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.CustomerDTO;
import myproject.ecommerse.dto.ItemDTO;
import myproject.ecommerse.dto.ReviewDTO;
import myproject.ecommerse.exception.CustomerNotFoundException;
import myproject.ecommerse.exception.ItemNotFoundException;
import myproject.ecommerse.model.Customer;
import myproject.ecommerse.model.Item;
import myproject.ecommerse.model.Review;
import myproject.ecommerse.repository.CustomerRepo;
import myproject.ecommerse.repository.ItemsRepo;
import myproject.ecommerse.repository.ReviewRepo;
import myproject.ecommerse.service.IReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepo reviewRepo;
    private final ItemsRepo itemsRepo;
    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;

   @Override
   public ReviewDTO addReview(ReviewDTO reviewDTO, int customerId, int itemId) {
       // Map DTO to Entity (only map non-relational fields)
       Review review = new Review();
       review.setTitle(reviewDTO.getTitle());
       review.setDescription(reviewDTO.getDescription());
       review.setNumberofStars(reviewDTO.getNumberofStars());
       review.setDate(reviewDTO.getDate());

       Customer customer = customerRepo.findById(customerId)
               .orElseThrow(() -> new CustomerNotFoundException("Customer with the Id  does not exist"));
       review.setCustomer(customer);

        Item item = itemsRepo.findById(itemId)
               .orElseThrow(() -> new ItemNotFoundException("Item not exist"));
       review.setItem(item);

       // Save the review
       Review savedReview = reviewRepo.save(review);

       // Create a new ReviewDTO from the saved entity (map only necessary fields)
       ReviewDTO responseDTO = new ReviewDTO();
       responseDTO.setReviewId(savedReview.getReviewId());
       responseDTO.setTitle(savedReview.getTitle());
       responseDTO.setDescription(savedReview.getDescription());
       responseDTO.setNumberofStars(savedReview.getNumberofStars());
       responseDTO.setDate(savedReview.getDate());
//       responseDTO.setCustomer(customer);
//       responseDTO.setItem(item);
//       responseDTO.setCustomer(customer)
//       responseDTO.setItemId(item.getItemId());
       CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
       responseDTO.setCustomer(customerDTO);

       ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
       responseDTO.setItem(itemDTO);

       return responseDTO;
   }

//    public ReviewDTO addReview(ReviewDTO review, int customerId, int itemId){
//
//        Review review1 = modelMapper.map(review, Review.class);
//        Customer customer = customerRepo.findById(customerId)
//                .orElseThrow(()->new CustomerNotFoundException("Customer with the Id "+ customerId+"does not exist"));
////        if(customer!=null & review.getReviewId() !=null){
//        review1.setCustomer(customer);
//
//           Item item = itemsRepo.findById(itemId)
//                   .orElseThrow(()->
//                    new ItemNotFoundException(" Item not exist"));
//        review1.setItem(item);
//
//
////        }
//
//        return modelMapper.map( reviewRepo.save(review1), ReviewDTO.class);
//    }

    @Override
    public List<ReviewDTO> getAllReviews() {
      List<Review> review = reviewRepo.findAll();
        return review.stream().map(review1 -> {
            ReviewDTO reviewDTO = modelMapper.map(review1, ReviewDTO.class);
            CustomerDTO customerDTO = modelMapper.map(review1.getCustomer(), CustomerDTO.class);
            ItemDTO itemDTO = modelMapper.map(review1.getItem(), ItemDTO.class);
            reviewDTO.setCustomer(customerDTO);
            reviewDTO.setItem(itemDTO);

            return reviewDTO;
        } ).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(int id) {
        return modelMapper.map( reviewRepo.findById(id).orElseThrow(()->new RuntimeException("no comment yet")), ReviewDTO.class);
    }

    @Override
    public void deleteReview(int id) {

      reviewRepo.deleteById(id);


    }
}
