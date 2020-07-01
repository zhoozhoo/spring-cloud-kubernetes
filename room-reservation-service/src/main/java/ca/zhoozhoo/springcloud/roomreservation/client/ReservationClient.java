package ca.zhoozhoo.springcloud.roomreservation.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ca.zhoozhoo.springcloud.roomreservation.model.Reservation;

@FeignClient("reservation-service")
public interface ReservationClient {

    @GetMapping("/reservations")
    public List<Reservation> getReservations(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date);

    @GetMapping("/reservations/{id}")
    public Reservation getReservation(@PathVariable("id") long id);
}
