package ch.coop.timetracker.stamptime;

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
public class StampTimeController {
    private final StampTimeService stampTimeService;

    public StampTimeController(StampTimeService stampTimeService) {
        this.stampTimeService = stampTimeService;
    }

    @GetMapping("api/stamptime")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<StampTime>> all() {
        List<StampTime> result = stampTimeService.getStampTimes();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/stamptime/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<StampTime> one(@PathVariable Long id) {
        StampTime stampTime = stampTimeService.getStampTime(id);
        return new ResponseEntity<>(stampTime, HttpStatus.OK);
    }

    @PostMapping("api/stamptime")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<StampTime> newStampTime(@Valid @RequestBody StampTime stampTime) {
        StampTime savedStampTime = stampTimeService.insertStampTime(stampTime);
        return new ResponseEntity<>(savedStampTime, HttpStatus.OK);
    }

    @PutMapping("api/stamptime/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<StampTime> udpateStampTime(@Valid @RequestBody StampTime stampTime, @PathVariable Long id) {
        StampTime savedStampTime = stampTimeService.updateStampTime(stampTime, id);
        return new ResponseEntity<>(savedStampTime, HttpStatus.OK);
    }

    @DeleteMapping("api/stamptime/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteStampTime(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stampTimeService.deleteStampTime(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
