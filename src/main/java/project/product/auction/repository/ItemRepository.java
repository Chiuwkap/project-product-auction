package project.product.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product.auction.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
