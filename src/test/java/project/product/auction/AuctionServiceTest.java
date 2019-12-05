package project.product.auction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.product.auction.model.Item;
import project.product.auction.repository.BidRepository;
import project.product.auction.repository.CustomerRepository;
import project.product.auction.repository.ItemRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuctionServiceTest {

    @Mock
    private BidRepository bidRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ItemRepository itemRepository;

    private Iterable<Item> listOfItems;
    private Item item1;
    private Item item2;
    private Item item3;

    @Before
    public void setUp() {
        item1 = new Item(1, "byrå","gustaviansk",BigDecimal.valueOf(10000.00),LocalDateTime.parse("2019-12-01T10:30:00"),"möbler", "http://example.com/byra.png", 10);
        item2 = new Item(2,"iPhone 6","Fint skick!",BigDecimal.valueOf(2000), LocalDateTime.parse("2019-12-10T12:30:00"), "elektronik", "http://example.com/mobil.png", 8);
        item3 = new Item(3,"kofta", "hemmastickad", BigDecimal.valueOf(200.00), LocalDateTime.parse("2019-12-13T15:50:00"), "kläder", "http://example.com/kofta.png", 15);
        Item[] items = {item1, item2, item3};
        listOfItems = Arrays.asList(items);

    }

    @Test
    public void getAllCurrentItemsExpiredTime_test() {
        when(itemRepository.findByExpTimeLessThanEqual(LocalDateTime.now()))
                .thenReturn(listOfItems);

        // To be continued...
    }
}
