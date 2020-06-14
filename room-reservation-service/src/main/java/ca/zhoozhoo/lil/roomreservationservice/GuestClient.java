package ca.zhoozhoo.lil.roomreservationservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("guestservices")
public interface GuestClient {

    @GetMapping("/guests")
    public List<Guest> getAllGuests();

    @GetMapping("/guests/{id}")
    public Guest getGuest(@PathVariable("id") long id);
}
