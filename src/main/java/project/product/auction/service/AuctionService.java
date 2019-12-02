package project.product.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.product.auction.dto.AuctionDto;
import project.product.auction.dto.BidDto;
import project.product.auction.model.Bid;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;
import project.product.auction.repository.BidRepository;
import project.product.auction.repository.CustomerRepository;
import project.product.auction.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class AuctionService {

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    BidRepository bidRepo;

    @Autowired
    CustomerRepository customerRepository;

    public Item registerItem(Item item) {
        return itemRepo.save(item);
    }

    public Iterable<Item> getAllItems() {
        return itemRepo.findAll();
    }

    public Iterable<Item> getAllCurrentItems() {
        return itemRepo.findByExpTimeLessThanEqual(LocalDateTime.now());
    }

    public Iterable<Item> getCurrentItemsByCategory(String cat) {
        return itemRepo.findByExpTimeLessThanEqualAndCategory(LocalDateTime.now(), cat);
    }

    public Optional<Customer> getProfile(long id){
        return customerRepository.findById(id);
    }

    public Optional<Bid> getAuction(long itemId) {
        return bidRepo.findFirstByItemIdOrderByBidTimeDesc(itemId);
    }

    public void removeItem(long itemId) { itemRepo.deleteById(itemId);}

    public String registerBid(BidDto bidDto) {
        // DidDTO   = itemId, customerId, bid
        // Bid      = itemId, customerId, bid, bidCount, BidTime
        // Get bid count:
        Optional<Bid> lastBid = bidRepo.findFirstByItemIdOrderByBidCountDesc(bidDto.getItemId());
        int lastBidCount = lastBid.get().getBidCount() + 1;
        BigDecimal lastBidPrice = lastBid.get().getBid();

        if (bidDto.getBid().compareTo(lastBid.get().getBid()) == 1) {
            bidRepo.save(new Bid(bidDto.getItemId(), bidDto.getCustomerID(), bidDto.getBid(), lastBidCount, LocalDateTime.now()));
            return "Entity persisted";
        } else {
            return "Bid must be higher than present bid";
        }
    }

    //TODO: To save bid:
    // 1. get bid count per itemId: select count(itemId) from bid where itemId = (itemId).
    // Use count + 1 in save (persist) of entityobject (Bid)
    // 2. Check that CurrentBid is above last value
}
