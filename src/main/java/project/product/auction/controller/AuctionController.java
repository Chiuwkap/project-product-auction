package project.product.auction.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.product.auction.model.Bid;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;
import project.product.auction.service.AuctionService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class AuctionController {

    @Autowired
    private AuctionService itemService;

    // Get all the items from the database. Not really necessary
    @ApiOperation(value = "Get all items", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/items")
    public Iterable<Item> getItems(){
        return itemService.getAllItems();
    }

    // Get a single customer by user id
    @ApiOperation(value = "Get user by id", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/profile/{userId}")
    public Optional<Customer> showProfile(@PathVariable long userId){
        return itemService.getProfile(userId);
    }

    // Get all items which haven't expired yet
    @ApiOperation(value = "Get all current items", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/currentauctions")
    public Iterable<Item> getCurrentAuctions() {
        return itemService.getAllCurrentItems();
    }

    // Same as above but by category
    @ApiOperation(value = "Get all current items by category", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/currentauctions/category/{cat}")
    public Iterable<Item> getCurrentAuctionsByCategory(@PathVariable String cat) {
        return itemService.getCurrentItemsByCategory(cat);
    }

    // Get the latest latest (by bid time) bid for a specific item (by item id)
    @ApiOperation(value = "Get the latest bid row for an item id", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved bid"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/auctions/{itemId}")
    public Optional<Bid> getAuction(@PathVariable long itemId) {
        return itemService.getAuction(itemId);
    }
}
