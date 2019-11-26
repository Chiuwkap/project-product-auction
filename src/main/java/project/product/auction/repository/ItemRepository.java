package project.product.auction.repository;

import org.springframework.data.repository.CrudRepository;
import project.product.auction.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
