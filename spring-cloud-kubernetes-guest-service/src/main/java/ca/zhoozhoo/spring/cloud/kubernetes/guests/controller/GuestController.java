package ca.zhoozhoo.spring.cloud.kubernetes.guests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.zhoozhoo.spring.cloud.kubernetes.guests.dao.GuestRepository;
import ca.zhoozhoo.spring.cloud.kubernetes.guests.model.Guest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    protected GuestRepository guestRepository;

    @GetMapping
    public Flux<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Guest> getGuest(@PathVariable("id") long id) {
        return guestRepository.findById(id);
    }
}
