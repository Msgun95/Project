package myproject.ecommerse.service.imp;

import lombok.RequiredArgsConstructor;

import myproject.ecommerse.dto.ItemDTO;
import myproject.ecommerse.exception.ItemNotFoundException;
import myproject.ecommerse.model.Item;
import myproject.ecommerse.repository.ItemsRepo;
import myproject.ecommerse.service.IItemsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemsService implements IItemsService {

    private final ItemsRepo itemsRepo;
    private final ModelMapper modelMapper;
    @Override
    public ItemDTO addItem(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);
        Item savedItem = itemsRepo.save(item);
        return modelMapper.map(savedItem, ItemDTO.class);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> itemList = itemsRepo.findAll();

        return itemList.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(int id) {
        Item  item = itemsRepo.findById(id).orElseThrow(()
                -> new ItemNotFoundException("Item does not exist"));
//        if(item.isPresent()){
//            Item item1 = item.get();

            return modelMapper.map(item, ItemDTO.class);
     //   }
//        else {
//            return  null;
//        }

    }

    @Override
    public void deleteItem(int id) {
        Item item= itemsRepo.findById(id).orElseThrow(()->
            new RuntimeException("The Item with ID " + id +" Is already deleted or not exist"));

        itemsRepo.delete(item);


    }

    @Override
    public ItemDTO updateItem(int id, ItemDTO itemDTO) {
        Item  existitem = itemsRepo.findById(id).orElseThrow(()
                -> new ItemNotFoundException("Item does not exist"));
        existitem.setName(itemDTO.getName());
        existitem.setDescription(itemDTO.getDescription());
        existitem.setPrice(itemDTO.getPrice());


        return modelMapper.map(itemsRepo.save(existitem), ItemDTO.class);
    }
}
