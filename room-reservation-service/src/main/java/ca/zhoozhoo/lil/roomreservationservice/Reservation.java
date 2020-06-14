package ca.zhoozhoo.lil.roomreservationservice;

import java.sql.Date;

import lombok.Data;

@Data
public class Reservation {

    private long id;

    private long roomId;

    private long guestId;

    private Date date;
}
