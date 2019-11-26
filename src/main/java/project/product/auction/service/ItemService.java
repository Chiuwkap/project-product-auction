package project.product.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.product.auction.model.Item;
import project.product.auction.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepo;

    public Item registerItem(Item item) {
        return itemRepo.save(item);
    }

    public Iterable<Item> getAllItem() {
        return itemRepo.findAll();
    }
}
