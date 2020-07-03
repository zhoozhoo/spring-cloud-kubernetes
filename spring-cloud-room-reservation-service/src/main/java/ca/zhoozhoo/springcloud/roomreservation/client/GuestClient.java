package ca.zhoozhoo.springcloud.roomreservation.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import ca.zhoozhoo.springcloud.roomreservation.model.Guest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GuestClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Flux<Guest> getAllGuests() {
        return webClientBuilder.build().get().uri("http://guest-service/guests").retrieve().bodyToFlux(Guest.class);
    }

    public Mono<Guest> getGuest(long id) {
        return webClientBuilder.build().get().uri("http://guest-service/guests/{id}", id).retrieve().bodyToMono(Guest.class);
    }
}
