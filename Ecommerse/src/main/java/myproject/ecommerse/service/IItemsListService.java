package myproject.ecommerse.service;

import myproject.ecommerse.model.ShoppingCart;

import java.util.List;

public interface IItemsListService {

    ShoppingCart addItemList(ShoppingCart shoppingCart);
    List<ShoppingCart> getAllItemLists();
    ShoppingCart getItemListById(int id);
    void deleteItemList(int id);
}
