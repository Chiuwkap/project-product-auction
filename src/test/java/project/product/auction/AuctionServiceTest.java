package project.product.auction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.product.auction.dto.AuctionDto;
import project.product.auction.dto.BidDto;
import project.product.auction.model.Bid;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;
import project.product.auction.repository.BidRepository;
import project.product.auction.repository.CustomerRepository;
import project.product.auction.repository.ItemRepository;
import project.product.auction.service.AuctionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuctionServiceTest {

    @Mock
    private BidRepository bidRepo;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ItemRepository itemRepo;

    @InjectMocks
    private AuctionService auctionService;

    private Iterable<Item> listOfItems;
    private Item item1;
    private Item item2;
    private Item item3;

    private Customer customer1;
    private Customer customer2;

    private Bid bid1;

    @Before
    public void setUp() {

        item1 = new Item(1, "byrå","gustaviansk",BigDecimal.valueOf(10000.00),LocalDateTime.parse("2019-12-01T10:30:00"),"möbler", "http://example.com/byra.png", 10);
        item2 = new Item(2,"iPhone 6","Fint skick!",BigDecimal.valueOf(2000), LocalDateTime.parse("2019-12-10T12:30:00"), "elektronik", "http://example.com/mobil.png", 8);
        item3 = new Item(3,"kofta", "hemmastickad", BigDecimal.valueOf(200.00), LocalDateTime.parse("2019-12-13T15:50:00"), "kläder", "http://example.com/kofta.png", 15);
        Item[] items = {item2, item3};
        listOfItems = Arrays.asList(items);

        customer1 = new Customer(2,"ECM","Eric","Cartman","990909-0909","Little Street","99999","South Park","0909-99999999","Cartman@southpark.com");

        bid1 = new Bid(1,1,BigDecimal.valueOf(12000.00), 1, LocalDateTime.parse("2019-11-15T12:30:00"));
    }


    @Test
    public void registerBidAccepted_test() {
        // Mock last bid in database
        Optional<Bid> lastBid = Optional.of(new Bid(1, 1,BigDecimal.valueOf(500.00), 1, LocalDateTime.parse("2019-12-03T10:00:00")));

        // The DTO as parameter in registerBid()
        BidDto bidDto = new BidDto(1, 2,BigDecimal.valueOf(600.00));

        // Return lastBid
        when(bidRepo.findFirstByItemIdOrderByBidCountDesc(bidDto.getItemId())).thenReturn(lastBid);

        // What registerBid() really returns
        Bid actual = auctionService.registerBid(bidDto, LocalDateTime.parse("2019-12-15T10:00:00"));

        // Assert that a bid has been created
        assertNotNull(actual);

        // Assert that new bid is higher than last bid - bid should be accepted
        assertTrue("Should return true", (actual.getBid().compareTo(lastBid.get().getBid()) == 1));
    }

    @Test
    public void registerBidNotAccepted_test() {
        // There is no last bid in database
        Optional<Bid> lastBid = Optional.empty();

        // The DTO as parameter in registerBid()
        BidDto bidDto = new BidDto(1, 2,BigDecimal.valueOf(8000.00));

        // Return lastBid (which is empty)
        when(bidRepo.findFirstByItemIdOrderByBidCountDesc(bidDto.getItemId())).thenReturn(lastBid);

        // Item in database
        Optional<Item> optionalItem = Optional.of(item1);

        // Return start price of item in database
        when(itemRepo.findById(bidDto.getItemId())).thenReturn(optionalItem);

        // What registerBid() really returns
        Bid actual = auctionService.registerBid(bidDto, LocalDateTime.parse("2019-12-15T10:00:00"));

        // Assert that a bid has not been created
        assertNull(actual);
    }

    @Test
    public void registerAnItem_test(){
        when(itemRepo.save(item2)).thenReturn(item2);
        Item item = auctionService.registerItem(item2);
        assertNotNull(item);
    }

    @Test
    public void registerACustomer_test(){

        when(customerRepository.save(customer1)).thenReturn(customer1);
        Customer customer = auctionService.registerCustomer(customer1);
        assertNotNull(customer);
    }

    @Test
    public void getAuctionWithBid_test() {
        long itemId = 1;
        Optional item = Optional.of(item1);
        Optional bid = Optional.of(bid1);

        when(itemRepo.findById(itemId)).thenReturn(item);
        when(bidRepo.findFirstByItemIdOrderByBidCountDesc(itemId)).thenReturn(bid);

        AuctionDto expected = auctionService.getAuction(1);

        assertNotNull(expected);
    }

    @Test
    public void getAuctionWithoutBid_test() {
        long itemId = 1;
        Optional item = Optional.of(item1);
        Optional bid = Optional.empty();

        when(itemRepo.findById(itemId)).thenReturn(item);
        when(bidRepo.findFirstByItemIdOrderByBidCountDesc(itemId)).thenReturn(bid);

        AuctionDto expected = auctionService.getAuction(1);

        assertNotNull(expected);
    }

    @Test
    public void removeItem_test() {
        long itemId = 1;
        Optional item = Optional.of(item1);

        when(itemRepo.findById(itemId)).thenReturn(item);

        Item deletedItem = auctionService.removeItem(itemId);

        assertNotNull(deletedItem);
        verify(itemRepo, times(1)).deleteById(itemId);
    }
}
