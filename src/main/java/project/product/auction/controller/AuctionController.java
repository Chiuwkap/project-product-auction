package project.product.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.product.auction.model.Item;
import project.product.auction.service.ItemService;


@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public Iterable<Item> getItems(){
        return itemService.getAllItems();
    }

}
