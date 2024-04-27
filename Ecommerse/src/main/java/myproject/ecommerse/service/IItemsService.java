package myproject.ecommerse.service;



import myproject.ecommerse.dto.ItemDTO;

import java.util.List;

public interface IItemsService {


    ItemDTO addItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();
    ItemDTO getItemById(int id);
    void deleteItem(int id);
    ItemDTO updateItem(int id, ItemDTO itemDTO);

}
