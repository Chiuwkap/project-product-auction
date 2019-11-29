package project.product.auction.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated ID")
    private long id;

    @Column(name = "item_name")
    @ApiModelProperty(notes = "Items name")
    private String itemName;

    @Column(name = "description")
    @ApiModelProperty(notes = "Items description")
    private String description;

    @Column(name = "exp_time")
    @ApiModelProperty(notes = "Expiration Time")
    private LocalDateTime expTime;

    @Column(name = "category")
    @ApiModelProperty(notes = "Items category")
    private String category;

    @Column(name = "image_url")
    @ApiModelProperty(notes = "URL to image")
    private String imageUrl;
}
