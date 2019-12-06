package project.product.auction.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuctionDto {

    // From Item
    private String name;
    private String desc;
    private LocalDateTime expTime;
    private String category;
    private String imageUrl;

    // From Bid
    private BigDecimal bid;
    private int bidCount;
    private LocalDateTime bidTime;
}
