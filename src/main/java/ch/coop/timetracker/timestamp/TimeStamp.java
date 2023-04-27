package ch.coop.timetracker.timestamp;

import ch.coop.timetracker.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class TimeStamp {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "time")
    private LocalDateTime time;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public TimeStamp() {}
}
