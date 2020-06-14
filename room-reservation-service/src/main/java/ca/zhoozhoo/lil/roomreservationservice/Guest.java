package ca.zhoozhoo.lil.roomreservationservice;

import lombok.Data;

@Data
public class Guest {

    private long id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String address;

    private String country;

    private String state;

    private String phoneNumber;
}
