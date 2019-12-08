package project.product.auction.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated ID")
    private long id;

    @Column(name = "alias")
    @ApiModelProperty(notes = "Customers alias")
    private String alias;

    @Column(name = "name")
    @ApiModelProperty(notes = "Customers name")
    private String name;

    @Column(name = "surname")
    @ApiModelProperty(notes = "Customers surname")
    private String surname;

    @Column(name = "person_number")
    @ApiModelProperty(notes = "Customers personnumber")
    private String personNumber;

    @Column(name = "address")
    @ApiModelProperty(notes = "Customers address")
    private String address;

    @Column(name = "zip_code")
    @ApiModelProperty(notes = "Customers zip code")
    private String zipCode;

    @Column(name = "post_town")
    @ApiModelProperty(notes = "Customers post town")
    private String postTown;

    @Column(name = "phone_number")
    @ApiModelProperty(notes = "Customers phonenumber")
    private String phoneNumber;

    @Column(name = "email")
    @ApiModelProperty(notes = "Customers email")
    private String email;

}
