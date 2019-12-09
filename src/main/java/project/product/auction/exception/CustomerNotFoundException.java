package project.product.auction.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.product.auction.controller.AuctionController;

public class CustomerNotFoundException extends RuntimeException {
    private static final Logger LOG = LoggerFactory.getLogger(AuctionController.class);

    public CustomerNotFoundException(long userId) {
        super("Customer id not found : " + userId);
        LOG.info("LOG INFO: Profile with this userId: " + userId + " not found");
    }
}
