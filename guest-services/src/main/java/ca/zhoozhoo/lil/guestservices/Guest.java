package ca.zhoozhoo.lil.guestservices;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table
@Data
public class Guest {

    @Id
    @Column("GUEST_ID")
    private Long id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String address;

    private String country;

    private String state;

    private String phoneNumber;
}
