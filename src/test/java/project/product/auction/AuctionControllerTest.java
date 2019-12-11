package project.product.auction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;
import project.product.auction.service.AuctionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AuctionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuctionService auctionsService;


    private Item item1;
    private Item item2;
    private Item item3;
    private Iterable<Item> listOfItems;
    private Customer customer1;

    @Before
    public void setUp() {

        item1 = new Item(1, "byrå", "gustaviansk", BigDecimal.valueOf(10000.00), LocalDateTime.parse("2019-12-01T10:30:00"), "möbler", "http://example.com/byra.png", 10);
        item2 = new Item(2, "iPhone 6", "Fint skick!", BigDecimal.valueOf(2000), LocalDateTime.parse("2019-12-10T12:30:00"), "elektronik", "http://example.com/mobil.png", 8);
        item3 = new Item(3, "kofta", "hemmastickad", BigDecimal.valueOf(200.00), LocalDateTime.parse("2019-12-13T15:50:00"), "kläder", "http://example.com/kofta.png", 15);
        Item[] items = {item1, item2, item3};
        listOfItems = Arrays.asList(items);

        customer1 = new Customer(1, "Kalle12", "Karl", "Dussin", "820617-4678", "Slottsbacken 1", "123 45", "Stockholm", "0701-123456", "karl@hovet.se");

    }

    @Test
    public void getItems_test() throws Exception {
        when(auctionsService.getAllItems()).thenReturn(listOfItems);

        this.mockMvc.perform(get("/v1/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].itemName", is("byrå")))
                .andExpect(jsonPath("$[1].itemName", is("iPhone 6")))
                .andExpect(jsonPath("$[2].itemName", is("kofta")));

    }

    @Test
    public void showProfile() throws Exception {
        Optional<Customer> customer = Optional.of(customer1);
        when(auctionsService.getProfile(1)).thenReturn(customer);

        this.mockMvc.perform(get("/v1/profile/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alias", is("Kalle12")));

        this.mockMvc.perform(get("/v1/profile/2"))
                .andExpect(status().isNotFound());
    }
}
