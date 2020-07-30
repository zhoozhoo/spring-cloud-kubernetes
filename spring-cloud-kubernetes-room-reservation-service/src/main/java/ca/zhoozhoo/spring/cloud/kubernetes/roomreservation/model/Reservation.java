package ca.zhoozhoo.spring.cloud.kubernetes.roomreservation.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Reservation {

    private long id;

    private long roomId;

    private long guestId;

    private LocalDate date;
}
