package ca.zhoozhoo.springcloud.roomreservation.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.zhoozhoo.springcloud.roomreservation.client.GuestClient;
import ca.zhoozhoo.springcloud.roomreservation.client.ReservationClient;
import ca.zhoozhoo.springcloud.roomreservation.client.RoomClient;
import ca.zhoozhoo.springcloud.roomreservation.model.RoomReservation;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationController {

    @Autowired
    protected RoomClient roomClient;

    @Autowired
    protected GuestClient guestClient;

    @Autowired
    protected ReservationClient reservationClient;

    @GetMapping
    public Flux<RoomReservation> getRoomReservations2(
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        Flux<RoomReservation> roomReservations =
                reservationClient.getReservations(date).publishOn(Schedulers.elastic()).map(reservation -> {
                    return roomClient.getRoom(reservation.getRoomId())
                            .zipWith(guestClient.getGuest(reservation.getGuestId()), (room, guest) -> {
                                RoomReservation roomReservation = new RoomReservation();
                                roomReservation.setDate(date);
                                roomReservation.setRoomId(room.getId());
                                roomReservation.setRoomName(room.getName());
                                roomReservation.setRoomNumber(room.getRoomNumber());
                                roomReservation.setGuestId(guest.getId());
                                roomReservation.setFirstName(guest.getFirstName());
                                roomReservation.setLastName(guest.getLastName());

                                return roomReservation;
                            }).block();
                });

        return roomReservations;
    }
}
