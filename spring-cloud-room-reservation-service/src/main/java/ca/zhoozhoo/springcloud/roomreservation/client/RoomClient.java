package ca.zhoozhoo.springcloud.roomreservation.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import ca.zhoozhoo.springcloud.roomreservation.model.Room;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RoomClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Flux<Room> getAllRooms() {
        return webClientBuilder.build().get().uri("http://room-service/rooms").retrieve().bodyToFlux(Room.class);
    }

    public Mono<Room> getRoom(long id) {
        return webClientBuilder.build().get().uri("http://room-service/rooms/{id}", id).retrieve()
                .bodyToMono(Room.class);
    }
}
