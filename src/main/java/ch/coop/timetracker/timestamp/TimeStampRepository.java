package ch.coop.timetracker.timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeStampRepository extends JpaRepository<TimeStamp, Long> {

}
