package ca.zhoozhoo.lil.guestservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guests")
public class GuestWebServices {

    private final GuestRepository guestRepository;

    public GuestWebServices(GuestRepository guestRepository) {
        super();
        this.guestRepository = guestRepository;
    }

    @GetMapping
    Iterable<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") long id) {
        return guestRepository.findById(id).get();
    }
}
