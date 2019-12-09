package project.product.auction.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.product.auction.dto.AuctionDto;
import project.product.auction.dto.BidDto;
import project.product.auction.exception.CustomerNotFoundException;
import project.product.auction.exception.NoItemToDeleteException;
import project.product.auction.model.Bid;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;
import project.product.auction.service.AuctionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/v1")
public class AuctionController {

    private static final Logger LOG = LoggerFactory.getLogger(AuctionController.class);

    @Autowired
    private AuctionService auctionsService;

    // Get all the items from the database. Not really necessary
    @ApiOperation(value = "Get all items", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/items")
    public Iterable<Item> getItems() {
        return auctionsService.getAllItems();
    }

    // Get a single customer by user id
    @ApiOperation(value = "Get user by id", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/profile/{userId}")
    public Customer showProfile(@PathVariable long userId) {

        return auctionsService.getProfile(userId).orElseThrow(()
                -> new CustomerNotFoundException(userId));
    }

    // Get all items which haven't expired yet
    @ApiOperation(value = "Get all current items which haven't expired yet", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/currentauctions")
    public ResponseEntity getCurrentAuctionsItems() {
        List<Item> items = StreamSupport.stream(auctionsService.getAllCurrentItemsNotExpiredTime().spliterator(), false)
                .collect(Collectors.toList());
        if (items.isEmpty()) {
            return new ResponseEntity("No items found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(items, HttpStatus.OK);
        }
    }

    // Get all items which haven't expired yet by category
    @ApiOperation(value = "Get all current items which haven't expired yet by category", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/currentauctions/category/{category}")
    public ResponseEntity getCurrentAuctionsItemsByCategory(@PathVariable String category) {
        List<Item> items = StreamSupport.stream(auctionsService.getCurrentItemsNotExpiredTimeByCategory(category).spliterator(), false)
                .collect(Collectors.toList());
        if (items.isEmpty()) {
            return new ResponseEntity("No items found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(items, HttpStatus.OK);
        }
    }

    // Get all items which has expired
    @ApiOperation(value = "Get all current items which have expired", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/expiredauctions")
    public ResponseEntity getExpiredAuctionsItems() {
        List<Item> items = StreamSupport.stream(auctionsService.getAllCurrentItemsExpiredTime().spliterator(), false)
                .collect(Collectors.toList());
        if (items.isEmpty()) {
            return new ResponseEntity("No items found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(items, HttpStatus.OK);
        }
    }

    // Get all items which has expired by category
    @ApiOperation(value = "Get all current items which has expired by category", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all expired items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/expiredauctions/category/{category}")
    public ResponseEntity getExpiredAuctionsItemsByCategory(@PathVariable String category) {
        List<Item> items = StreamSupport.stream(auctionsService.getCurrentItemsExpiredTimeByCategory(category).spliterator(), false)
                .collect(Collectors.toList());
        if (items.isEmpty()) {
            return new ResponseEntity("No items found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(items, HttpStatus.OK);
        }
    }

    // Get the latest latest (by bid time) bid for a specific item (by item id)
    @ApiOperation(value = "Get the latest bid row for an item id", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved bid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/auctions/{itemId}")
    public ResponseEntity getAuction(@PathVariable long itemId) {
        AuctionDto auctionDto = auctionsService.getAuction(itemId);
        if (auctionDto == null) {
            return new ResponseEntity("No auction with such item id", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(auctionDto, HttpStatus.OK);
        }
    }

    // Delete item with itemId
    @ApiOperation(value = "Delete an item with ItemId", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Deletion was successful"),
            @ApiResponse(code = 404, message = "No such item")})
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity removeItemFromAuction(@PathVariable long itemId) {

        Item deletedItem = auctionsService.removeItem(itemId);
        if (deletedItem != null) {
            LOG.info("LOG INFO: Item successfully deleted, item id: " + itemId);
            return new ResponseEntity("Deletion Success", HttpStatus.OK);
        } else if(deletedItem == null){
            LOG.info("LOG INFO: No such item to remove");
            throw new NoItemToDeleteException(itemId);
        }
        return null;
    }

    // Register bid
    @ApiOperation(value = "Register a bid in a auction", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Bid accepted"),
            @ApiResponse(code = 406, message = "Bid not accepted")})
    @PostMapping("/bid/register")
    public ResponseEntity registerBid(@RequestBody BidDto bidDto) {

        Bid newBid = auctionsService.registerBid(bidDto, LocalDateTime.now());

        if (newBid != null) {
            LOG.info("LOG INFO: Highest bid is: " + newBid.getBid() + " on item " + bidDto);
            return new ResponseEntity("Bid accepted", HttpStatus.ACCEPTED);

        } else {
            LOG.info("LOG INFO: Bid not accepted, item " + bidDto);
            return new ResponseEntity("Bid not accepted", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // Register item
    @PostMapping("/item/register")
    public ResponseEntity registerItem(@RequestBody Item item) {
       auctionsService.registerItem(item);
       LOG.info("LOG INFO: Item: " + item + " added");
       return new ResponseEntity("Item added", HttpStatus.OK);
    }

    // Register customer
    @PostMapping("/customer/register")
    public ResponseEntity registerCustomer(@RequestBody Customer customer) {
        auctionsService.registerCustomer(customer);
        LOG.info("LOG INFO: Customer: " + customer + " added");
        return new ResponseEntity("Customer added", HttpStatus.OK);
    }
}
