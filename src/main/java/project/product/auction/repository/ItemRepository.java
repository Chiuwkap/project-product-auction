package project.product.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product.auction.model.Item;

import java.time.LocalDateTime;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Iterable<Item> findByExpTimeLessThanEqual(LocalDateTime expTime);
    Iterable<Item> findByExpTimeLessThanEqualAndCategory(LocalDateTime expTime, String category);

}