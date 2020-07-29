package ca.zhoozhoo.spring.cloud.kubernetes.guests.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.zhoozhoo.spring.cloud.kubernetes.guests.model.Guest;

@Repository
public interface GuestRepository extends ReactiveCrudRepository<Guest, Long> {
}
