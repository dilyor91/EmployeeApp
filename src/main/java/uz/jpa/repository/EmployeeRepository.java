package uz.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jpa.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee>findByLastname(String nomi);
    List<Employee>findByLastnameAndDepartment_Id(String nomi,Integer depnomi);
    List<Employee>findByDepartment_Id(Integer depnomi);
}
