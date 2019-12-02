package project.product.auction.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @ApiOperation(value = "Get all items", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/items")
    public Iterable<Item> getItems(){
        return itemService.getAllItems();
    }

    @ApiOperation(value = "Get user by id", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/profile/{userId}")
    public Optional<Customer> showProfile(@PathVariable long userId){
        return itemService.getProfile(userId);
    }

    @ApiOperation(value = "Get all current items", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/currentauctions")
    public Iterable<Item> getCurrentAuctions() {
        return itemService.getAllCurrentItems();
    }

    @ApiOperation(value = "Get all current items by category", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/currentauctions/category/{cat}")
    public Iterable<Item> getCurrentAuctionsByCategory(@PathVariable String cat) {
        return itemService.getCurrentItemsByCategory(cat);
    }

    @GetMapping("/auctions/{itemId}")
    public Optional<Bid> getAuction(@PathVariable long itemId) {
        return itemService.getAuction(itemId);
    }

    // Delete item with itemId
    @DeleteMapping("/items/{itemId}")
    public void removeItemFromAuction(@PathVariable long itemId) { itemService.removeItem(itemId); }
}
