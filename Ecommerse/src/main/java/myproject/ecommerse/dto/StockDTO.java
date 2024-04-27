package myproject.ecommerse.dto;

import jakarta.persistence.OneToOne;
import myproject.ecommerse.model.Item;

public class StockDTO {

    private Integer itemId;
    private int quantityAvailable;




    private ItemDTO item;
}
