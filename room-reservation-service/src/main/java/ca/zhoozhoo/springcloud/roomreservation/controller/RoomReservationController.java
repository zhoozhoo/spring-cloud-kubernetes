package ca.zhoozhoo.springcloud.roomreservation.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.zhoozhoo.springcloud.roomreservation.client.GuestClient;
import ca.zhoozhoo.springcloud.roomreservation.client.ReservationClient;
import ca.zhoozhoo.springcloud.roomreservation.client.RoomClient;
import ca.zhoozhoo.springcloud.roomreservation.model.Guest;
import ca.zhoozhoo.springcloud.roomreservation.model.Reservation;
import ca.zhoozhoo.springcloud.roomreservation.model.Room;
import ca.zhoozhoo.springcloud.roomreservation.model.RoomReservation;

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
    public List<RoomReservation> getRoomReservations(
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Room> rooms = this.roomClient.getAllRooms();
        Map<Long, RoomReservation> roomReservations = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.put(room.getId(), roomReservation);
        });
        List<Reservation> reservations = this.reservationClient.getReservations(date);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestClient.getGuest(reservation.getGuestId());
            roomReservation.setGuestId(guest.getId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        });

        return new ArrayList<>(roomReservations.values());
    }
}
