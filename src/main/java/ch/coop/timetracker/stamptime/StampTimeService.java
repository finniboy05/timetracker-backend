package ch.coop.timetracker.stamptime;

import ch.coop.timetracker.base.MessageResponse;
import ch.coop.timetracker.dataaccess.EntityNotFoundException;
import ch.coop.timetracker.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StampTimeService {
    private final StampTimeRepository stampTimeRepository;

    public StampTimeService(StampTimeRepository stampTimeRepository) {
        this.stampTimeRepository = stampTimeRepository;
    }

    public List<StampTime> getStampTimes() {
        return stampTimeRepository.findAll();
    }

    public StampTime getStampTime(Long id) {
        return stampTimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, StampTime.class));
    }

    public StampTime insertStampTime(StampTime stampTime) {
        return stampTimeRepository.save(stampTime);
    }

    public StampTime updateStampTime(StampTime stampTime, Long id) {
        return stampTimeRepository.findById(id)
                .map(employeeOrig -> {
                    employeeOrig.setTime(stampTime.getTime());
                    return stampTimeRepository.save(employeeOrig);
                })
                .orElseGet(() -> stampTimeRepository.save(stampTime));

    }

    public MessageResponse deleteStampTime(Long id) {
        stampTimeRepository.deleteById(id);
        return new MessageResponse("StampTime " + id + " deleted");

    }
}
