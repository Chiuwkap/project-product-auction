package project.product.auction.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated ID")
    private long id;

    @Column(name = "item_id")
    @ApiModelProperty(notes = "Item Id")
    private long itemId;

    @Column(name = "customer_id")
    @ApiModelProperty(notes = "Customers Id")
    private long customerId;

    @Digits(integer=10, fraction=2)
    @Column(name = "bid")
    @ApiModelProperty(notes = "Bids of the item")
    private int bid;

    @Column(name = "bid_count")
    @ApiModelProperty(notes = "Shows how many times item is bid")
    private int bidCount;

    @Column(name = "bid_time")
    @ApiModelProperty(notes = "Time of bid")
    private LocalDateTime bidTime;
}
