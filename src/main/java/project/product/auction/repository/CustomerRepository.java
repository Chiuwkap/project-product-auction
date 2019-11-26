package project.product.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product.auction.model.Customer;

public interface CustomerRepository extends JpaRepository<Long, Customer> {
}
