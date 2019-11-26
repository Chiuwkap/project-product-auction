package project.product.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product.auction.model.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
