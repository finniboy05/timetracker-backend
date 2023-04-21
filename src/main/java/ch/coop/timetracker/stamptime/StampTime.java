package ch.coop.timetracker.stamptime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class StampTime {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime time;

    public StampTime() {}
}
