package ca.zhoozhoo.lil.reservationservices;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table
@Data
public class Reservation {

    @Id
    @Column("RESERVATION_ID")
    private long id;

    private long roomId;

    private long guestId;

    @Column("RES_DATE")
    private Date date;
}
