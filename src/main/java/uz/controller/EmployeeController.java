package uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.jpa.entity.Department;
import uz.jpa.entity.Employee;
import uz.jpa.repository.DepartmentRepository;
import uz.jpa.repository.EmployeeRepository;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/index")
    public String index(Map<String,Object>model){
        List<Employee>employeeList=employeeRepository.findAll();
        model.put("employees",employeeList);
        model.put("departments",departmentRepository.findAll());
        return "/employee/index";
    }


    @GetMapping("/create")
    public String create(Map<String,Object> model){

        List<Department> departmentList=departmentRepository.findAll();
        model.put("departments",departmentList);
        return "/employee/create";
    }
    @PostMapping("/create2")
    public String createPost(Employee employee,Map<String,Object>model){
        employeeRepository.save(employee);
        model.put("employees",employeeRepository.findAll());
        return "/employee/index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Map<String,Object>model,@PathVariable(name = "id", required = false) Integer id)
    {
        employeeRepository.deleteById(id);
        model.put("employees",employeeRepository.findAll());
        return "/employee/index";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Map<String,Object>model,@PathVariable(name = "id", required = false) Integer id)
    {
        model.put("employee",employeeRepository.getOne(id));
        model.put("departments",departmentRepository.findAll());
        return "/employee/edit";
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(@RequestParam String nomi,@RequestParam Integer department,Map<String,Object>model)
    {
        List<Employee>employeeList=employeeRepository.findAll();
        if(nomi!=null && department!=null){
           employeeList=employeeRepository.findByLastnameAndDepartment_Id(nomi,department);
        }
        if(nomi!=null){
            employeeList=employeeRepository.findByLastname(nomi);
        }
        if(department!=null){
            employeeList=employeeRepository.findByDepartment_Id(department);
        }
          model.put("employees",employeeList);
          return "/employee/index";
    }
}
