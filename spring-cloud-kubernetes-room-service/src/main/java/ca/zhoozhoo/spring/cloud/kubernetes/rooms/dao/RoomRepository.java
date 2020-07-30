package ca.zhoozhoo.spring.cloud.kubernetes.rooms.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.zhoozhoo.spring.cloud.kubernetes.rooms.model.Room;

@Repository
public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {
}
