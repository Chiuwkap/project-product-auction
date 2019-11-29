package project.product.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;
import project.product.auction.repository.CustomerRepository;
import project.product.auction.repository.ItemRepository;

import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class AuctionService {

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    CustomerRepository customerRepository;

    public Item registerItem(Item item) {
        return itemRepo.save(item);
    }

    public Iterable<Item> getAllItems() {
        return itemRepo.findAll();
    }
    public Iterable<Item> getAllCurrentItems() {
        return itemRepo.findByExpTimeGreaterThanEqual(LocalDateTime.now());
    }

    public Iterable<Item> getCurrentItemsByCategory(String cat) {
        return itemRepo.findByExpTimeGreaterThanEqualAndCategory(LocalDateTime.now(), cat);
    }

    public Optional<Customer> getProfile(Long id){
        return customerRepository.findById(id);
    }

    //TODO: to get bid count per itemId: select count(itemId) from bid where itemId = (itemId).
    // Use count + 1 in save (persist) of entityobject (Bid)
}
