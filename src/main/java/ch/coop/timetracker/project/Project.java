package ch.coop.timetracker.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

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
