package project.product.auction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.product.auction.dto.BidDto;
import project.product.auction.model.Bid;
import project.product.auction.model.Item;
import project.product.auction.repository.BidRepository;
import project.product.auction.repository.CustomerRepository;
import project.product.auction.repository.ItemRepository;
import project.product.auction.service.AuctionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuctionServiceTest {

    @Mock
    private BidRepository bidRepo;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private AuctionService auctionService;

    private Iterable<Item> listOfItems;
    private Item item1;
    private Item item2;
    private Item item3;
    Optional<Bid> lastBid;
    private BidDto bidDto;

    @Before
    public void setUp() {
        item1 = new Item(1, "byrå","gustaviansk",BigDecimal.valueOf(10000.00),LocalDateTime.parse("2019-12-01T10:30:00"),"möbler", "http://example.com/byra.png", 10);
        item2 = new Item(2,"iPhone 6","Fint skick!",BigDecimal.valueOf(2000), LocalDateTime.parse("2019-12-10T12:30:00"), "elektronik", "http://example.com/mobil.png", 8);
        item3 = new Item(3,"kofta", "hemmastickad", BigDecimal.valueOf(200.00), LocalDateTime.parse("2019-12-13T15:50:00"), "kläder", "http://example.com/kofta.png", 15);
        Item[] items = {item1, item2, item3};
        listOfItems = Arrays.asList(items);



    }

    @Test
    public void registerBid_test() {
        // Mock last bid in database
        lastBid = Optional.of(new Bid(1, 1,BigDecimal.valueOf(500.00), 1, LocalDateTime.parse("2019-12-03T10:00:00")));

        // The DTO as parameter in registerBid()
        bidDto = new BidDto(1, 2,BigDecimal.valueOf(600.00));

        // Return lastBid
        when(bidRepo.findFirstByItemIdOrderByBidCountDesc(bidDto.getItemId())).thenReturn(lastBid);

        // What registerBid() really returns
        Bid actual = auctionService.registerBid(bidDto, LocalDateTime.parse("2019-12-15T10:00:00"));

        // Test that new bid is higher than last bid
        assertTrue("New bid is higher than last bid", (actual.getBid().compareTo(lastBid.get().getBid()) > 0));


    }
}
