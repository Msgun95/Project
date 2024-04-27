package myproject.ecommerse.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String ordernotexist) {
        super(ordernotexist);

    }
}
