package project.product.auction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private ItemService itemService;

}
