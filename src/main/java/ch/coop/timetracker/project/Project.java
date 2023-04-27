package ch.coop.timetracker.project;

import ch.coop.timetracker.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;

    public Project() {}
}
