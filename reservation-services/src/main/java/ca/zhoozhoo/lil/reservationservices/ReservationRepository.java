package ca.zhoozhoo.lil.reservationservices;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    public Iterable<Reservation> findAllByDate(Date date);
}
