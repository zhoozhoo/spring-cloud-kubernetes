package ca.zhoozhoo.springcloud.roomreservation.client;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import ca.zhoozhoo.springcloud.roomreservation.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReservationClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Flux<Reservation> getReservations(LocalDate date) {
        return webClientBuilder.build().get().uri("http://reservation-service/reservations", date).retrieve()
                .bodyToFlux(Reservation.class);
    }

    public Mono<Reservation> getReservation(long id) {
        return webClientBuilder.build().get().uri("http://reservation-service/reservation/{id}", id).retrieve()
                .bodyToMono(Reservation.class);
    }
}
