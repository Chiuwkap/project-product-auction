package project.product.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;
import project.product.auction.repository.ItemRepository;

import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    ItemRepository itemRepo;

    public Item registerItem(Item item) {
        return itemRepo.save(item);
    }

    public Iterable<Item> getAllItems() {
        return itemRepo.findAll();
    }

    public Optional<Customer> getProfile(){ return itemRepo.findById();}

    //TODO: to get bid count per itemId: select count(itemId) from bid where itemId = (itemId).
    // Use count + 1 in save (persist) of entityobject (Bid)
}
