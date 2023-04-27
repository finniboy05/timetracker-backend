package ch.coop.timetracker.timestamp;

import ch.coop.timetracker.base.MessageResponse;
import ch.coop.timetracker.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class TimeStampController {
    private final TimeStampService timeStampService;

    public TimeStampController(TimeStampService timeStampService) {
        this.timeStampService = timeStampService;
    }

    @GetMapping("api/timestamp")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<TimeStamp>> all() {
        List<TimeStamp> result = timeStampService.getTimeStamps();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/timestamp/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<TimeStamp> one(@PathVariable Long id) {
        TimeStamp timeStamp = timeStampService.getTimeStamp(id);
        return new ResponseEntity<>(timeStamp, HttpStatus.OK);
    }

    @PostMapping("api/timestamp")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<TimeStamp> newTimeStamp(@Valid @RequestBody TimeStamp timeStamp) {
        TimeStamp savedTimeStamp = timeStampService.insertTimeStamp(timeStamp);
        return new ResponseEntity<>(savedTimeStamp, HttpStatus.OK);
    }

    @PutMapping("api/timestamp/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<TimeStamp> updateTimeStamp(@Valid @RequestBody TimeStamp timeStamp, @PathVariable Long id) {
        TimeStamp savedTimeStamp = timeStampService.updateTimeStamp(timeStamp, id);
        return new ResponseEntity<>(savedTimeStamp, HttpStatus.OK);
    }

    @DeleteMapping("api/timestamp/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteTimeStamp(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(timeStampService.deleteTimeStamp(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
