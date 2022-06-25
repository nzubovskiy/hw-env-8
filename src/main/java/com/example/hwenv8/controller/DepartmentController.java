package com.example.hwenv8.controller;

import com.example.hwenv8.model.Employee;
import com.example.hwenv8.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam ("departmentID") Integer department) {
        return departmentService.maxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam ("departmentID") Integer department) {
        return departmentService.minSalary(department);
    }

    @GetMapping("/all?")
    public Employee getEmployeeByDepartment(@RequestParam ("departmentID") Integer department) {
        return (Employee) departmentService.getEmployeeByDepartment(department);
    }

    @GetMapping("/all")
    public Employee allEmployee() {
        return departmentService.allEmployee();
    }

}
