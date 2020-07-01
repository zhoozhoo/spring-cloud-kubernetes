package ca.zhoozhoo.springcloud.roomreservation.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.zhoozhoo.springcloud.roomreservation.model.Guest;

@FeignClient("guest-service")
public interface GuestClient {

    @GetMapping("/guests")
    public List<Guest> getAllGuests();

    @GetMapping("/guests/{id}")
    public Guest getGuest(@PathVariable("id") long id);
}
