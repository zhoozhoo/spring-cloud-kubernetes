package ca.zhoozhoo.lil.roomservices;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {
}
