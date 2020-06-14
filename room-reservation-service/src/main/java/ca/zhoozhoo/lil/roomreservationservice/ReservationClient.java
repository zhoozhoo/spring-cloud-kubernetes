package ca.zhoozhoo.lil.roomreservationservice;

import java.sql.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("reservationservices")
public interface ReservationClient {

    @GetMapping("/reservations")
    public List<Reservation> getReservations(@RequestParam(name = "date", required = false) Date date);

    @GetMapping("/reservations/{id}")
    public Reservation getReservation(@PathVariable("id") long id);
}
