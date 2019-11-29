package project.product.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Iterable<Item> findByExpTimeGreaterThanEqual(LocalDateTime expTime);
    Iterable<Item> findByExpTimeGreaterThanEqualAndCategory(LocalDateTime expTime, String category);

}