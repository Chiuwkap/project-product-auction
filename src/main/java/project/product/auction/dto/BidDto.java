package project.product.auction.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BidDto {

    private long itemId;
    private long customerID;
    private BigDecimal bid;
}
