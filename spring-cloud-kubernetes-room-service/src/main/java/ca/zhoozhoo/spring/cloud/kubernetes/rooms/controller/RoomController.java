package ca.zhoozhoo.spring.cloud.kubernetes.rooms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.zhoozhoo.spring.cloud.kubernetes.rooms.dao.RoomRepository;
import ca.zhoozhoo.spring.cloud.kubernetes.rooms.model.Room;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono; 

@RestController
@RequestMapping("/rooms")
public class RoomController {

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
