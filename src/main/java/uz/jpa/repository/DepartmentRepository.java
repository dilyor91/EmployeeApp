package uz.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jpa.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
