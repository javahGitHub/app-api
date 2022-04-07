package pdp.uz.appapi.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

}
