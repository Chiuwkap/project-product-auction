package project.product.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product.auction.model.Item;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Iterable<Item> findByExpTimeLessThanEqual(LocalDateTime expirationTime);
    Iterable<Item> findByExpTimeLessThanEqualAndCategory(LocalDateTime expirationTime, String category);

    Iterable<Item> findByExpTimeGreaterThanEqual(LocalDateTime expirationTime);
    Iterable<Item> findByExpTimeGreaterThanEqualAndCategory(LocalDateTime expirationTime, String category);

    Optional<Item> deleteById(long id);

}