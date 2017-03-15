package persistence.model;

//import java.util.List;
import javax.persistence.*;
import java.util.Collection;

@Entity
public class Employee {
  @Id private int id;
  private String name;
  private long salary;
  @ManyToMany
  @JoinTable(name="EMP_PROJ",
          joinColumns=@JoinColumn(name="EMP_ID"),
          inverseJoinColumns=@JoinColumn(name="PROJ_ID"))
  private Collection<Project> projects;
  public Employee() {}
  public Employee(int id) { this.id = id; }
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public long getSalary() { return salary; }
  public void setSalary (long salary) { this.salary = salary; }
}