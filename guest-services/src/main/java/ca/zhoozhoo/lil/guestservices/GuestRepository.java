package ca.zhoozhoo.lil.guestservices;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends ReactiveCrudRepository<Guest, Long> {
}
