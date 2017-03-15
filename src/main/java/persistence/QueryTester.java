package persistence;

import java.io.*;
import java.util.*;
import javax.persistence.*;

import org.apache.commons.lang.builder.*;
import persistence.model.Employee;
import persistence.model.Project;

public class QueryTester {

    static EntityManagerFactory emf;
    static EntityManager em;

    static Employee createEmployee(int id, String name, long salary) {
        Employee emp = new Employee(id);
        emp.setName(name);
        emp.setSalary(salary);
        em.persist(emp);
        return emp;
    }

    public static void main(String[] args) throws Exception {

        emf = Persistence.createEntityManagerFactory("microtest");
        em = emf.createEntityManager();

	    //create stuff
        em.getTransaction().begin();
        Project proj = new Project(1);
        proj.addEmployee(createEmployee(1, "employee1", 1000));
        proj.addEmployee(createEmployee(2, "employee2", 2000));
        proj.addEmployee(createEmployee(3, "employee3", 3000));
        proj.setName("subbotnik");
        em.persist(proj);
        em.getTransaction().commit();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        for (; ; ) {
            System.out.print("JP QL> ");
            String query = reader.readLine();
            if (query.equals("quit")) {
                break;
            }
            if (query.length() == 0) {
                continue;
            }

            try {
                List result = em.createQuery(query).getResultList();
                if (result.size() > 0) {
                    int count = 0;
                    for (Object o : result) {
                        System.out.print(++count + " ");
                        printResult(o);
                    }
                } else {
                    System.out.println("0 results returned");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printResult(Object result) throws Exception {
        if (result == null) {
            System.out.print("NULL");
        } else if (result instanceof Object[]) {
            Object[] row = (Object[]) result;
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                printResult(row[i]);
            }
            System.out.print("]");
        } else if (result instanceof Long ||
                result instanceof Double ||
                result instanceof String) {
            System.out.print(result.getClass().getName() + ": " + result);
        } else {
            System.out.print(ReflectionToStringBuilder.toString(result,
                    ToStringStyle.SHORT_PREFIX_STYLE));
        }
        System.out.println();
    }
}
