package persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin1 on 14.03.2017.
 */
@Entity
public class Project {
    @Id private int id;
    private String name;
    @ManyToMany(mappedBy="projects")
    private List<Employee> employees = new ArrayList<>();

    public Project() {}
    public Project(int id) { this.id = id; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    };
}