package project.product.auction.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private long custId;

    @Column(name = "current_bid")
    @ApiModelProperty(notes = "Shows current price")
    private int currentBid;

    @Column(name = "bid_count")
    @ApiModelProperty(notes = "Shows how many time item is bid")
    private int bidCount;
}
