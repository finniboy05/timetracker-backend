package ch.coop.timetracker.stamptime;

import ch.coop.timetracker.dataaccess.EntityNotFoundException;
import ch.coop.timetracker.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StampTimeRepository extends JpaRepository<StampTime, Long> {

}
