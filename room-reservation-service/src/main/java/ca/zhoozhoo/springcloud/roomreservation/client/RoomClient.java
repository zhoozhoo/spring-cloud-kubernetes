package ca.zhoozhoo.springcloud.roomreservation.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.zhoozhoo.springcloud.roomreservation.model.Room;

@FeignClient("room-service")
public interface RoomClient {

    @GetMapping("/rooms")
    public List<Room> getAllRooms();

    @GetMapping("/rooms/{id}")
    public Room getRoom(@PathVariable("id") long id);
}