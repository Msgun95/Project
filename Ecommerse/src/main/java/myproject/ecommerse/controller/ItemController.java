package myproject.ecommerse.controller;

import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.ItemDTO;
import myproject.ecommerse.service.IItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {

    private final IItemsService iItemsService;

    @PostMapping
    public ResponseEntity<ItemDTO> AddItem(@RequestBody ItemDTO item){
        return ResponseEntity.ok(iItemsService.addItem(item));


    }
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable int id) {
       return ResponseEntity.ok(iItemsService.getItemById(id));
    }
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return ResponseEntity.ok(iItemsService.getAllItems());
    }
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id){
        iItemsService.deleteItem(id);
    }

    @PutMapping("/{id}")
public ResponseEntity<ItemDTO> updateItem(@PathVariable int id, @RequestBody ItemDTO itemDTO){
        return ResponseEntity.ok(iItemsService.updateItem(id, itemDTO));

    }

}
