package ch.coop.timetracker.timestamp;

import ch.coop.timetracker.base.MessageResponse;
import ch.coop.timetracker.dataaccess.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeStampService {
    private final TimeStampRepository timeStampRepository;

    public TimeStampService(TimeStampRepository timeStampRepository) {
        this.timeStampRepository = timeStampRepository;
    }

    public List<TimeStamp> getTimeStamps() {
        return timeStampRepository.findAll();
    }

    public TimeStamp getTimeStamp(Long id) {
        return timeStampRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, TimeStamp.class));
    }

    public TimeStamp insertTimeStamp(TimeStamp timeStamp) {
        return timeStampRepository.save(timeStamp);
    }

    public TimeStamp updateTimeStamp(TimeStamp timeStamp, Long id) {
        return timeStampRepository.findById(id)
                .map(employeeOrig -> {
                    employeeOrig.setTime(timeStamp.getTime());
                    return timeStampRepository.save(employeeOrig);
                })
                .orElseGet(() -> timeStampRepository.save(timeStamp));

    }

    public MessageResponse deleteTimeStamp(Long id) {
        timeStampRepository.deleteById(id);
        return new MessageResponse("TimeStamp " + id + " deleted");

    }

}
