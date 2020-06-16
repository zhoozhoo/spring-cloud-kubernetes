package ca.zhoozhoo.lil.roomservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rooms")
public class RoomWebService {

    @Autowired
    protected RoomRepository repository;

    @GetMapping
    public Flux<Room> getAllRooms() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Room> getRoom(@PathVariable("id") long id) {
        return this.repository.findById(id);
    }
}
