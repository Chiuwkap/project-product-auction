package project.product.auction.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.product.auction.controller.AuctionController;

public class NoItemToDeleteException extends RuntimeException {
    private static final Logger LOG = LoggerFactory.getLogger(AuctionController.class);

    public NoItemToDeleteException(long itemId) {
        super("Item id not found : " + itemId);
        LOG.info("LOG INFO: Item with this ItemId: " + itemId + " not found, no item to be deleted");
    }
}