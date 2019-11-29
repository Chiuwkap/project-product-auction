package project.product.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product.auction.model.Customer;
import project.product.auction.model.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

   Optional<Customer> findById();

}
