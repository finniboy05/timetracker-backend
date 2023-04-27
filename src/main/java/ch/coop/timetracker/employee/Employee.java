package ch.coop.timetracker.employee;

import ch.coop.timetracker.department.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @NotEmpty
    private String name;
    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @NotEmpty
    private String firstname;
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {}
}
