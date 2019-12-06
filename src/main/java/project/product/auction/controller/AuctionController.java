package project.product.auction.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.product.auction.dto.BidDto;
import project.product.auction.model.Bid;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;
import project.product.auction.service.AuctionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class AuctionController {

    private static final Logger LOG = LogManager.getLogger(AuctionController.class);

    @Autowired
    private AuctionService itemService;

    // Get all the items from the database. Not really necessary
    @ApiOperation(value = "Get all items", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/items")
    public Iterable<Item> getItems() {
        return itemService.getAllItems();
    }

    // Get a single customer by user id
    @ApiOperation(value = "Get user by id", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/profile/{userId}")
    public Optional<Customer> showProfile(@PathVariable long userId) {
        return itemService.getProfile(userId);
    }

    // Get all items which haven't expired yet
    @ApiOperation(value = "Get all current items which haven't expired yet", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/currentauctions")
    public Iterable<Item> getCurrentAuctionsItems() {
        return itemService.getAllCurrentItemsNotExpiredTime();
    }

    // Get all items which haven't expired yet by category
    @ApiOperation(value = "Get all current items which haven't expired yet by category", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/currentauctions/category/{category}")
    public Iterable<Item> getCurrentAuctionsItemsByCategory(@PathVariable String category) {
        return itemService.getCurrentItemsNotExpiredTimeByCategory(category);
    }

    // Get all items which has expired
    @ApiOperation(value = "Get all current items which have expired", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/expiredauctions")
    public Iterable<Item> getExpiredAuctionsItems() {
        return itemService.getAllCurrentItemsExpiredTime();
    }

    // Get all items which has expired by category
    @ApiOperation(value = "Get all current items which has expired by category", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved all expired items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/expiredauctions/category/{category}")
    public Iterable<Item> getExpiredAuctionsItemsByCategory(@PathVariable String category) {
        return itemService.getCurrentItemsExpiredTimeByCategory(category);
    }

    // Get the latest latest (by bid time) bid for a specific item (by item id)
    @ApiOperation(value = "Get the latest bid row for an item id", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved bid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/auctions/{itemId}")
    public Optional<Bid> getAuction(@PathVariable long itemId) {
        return itemService.getAuction(itemId);
    }

    // Delete item with itemId
    @ApiOperation(value = "Delete an item with ItemId", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Deletion was successful"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity removeItemFromAuction(@PathVariable long itemId) {
        Item deletedItem = itemService.removeItem(itemId);
        if (deletedItem != null) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    // Register bid
    @ApiOperation(value = "Register a bid in a auction", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Bid accepted"),
            @ApiResponse(code = 406, message = "Bid not accepted")})
    @PostMapping("/bid/register")
    public ResponseEntity registerBid(@RequestBody BidDto bidDto) {
        Bid newBid = itemService.registerBid(bidDto);
        if (newBid != null) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
