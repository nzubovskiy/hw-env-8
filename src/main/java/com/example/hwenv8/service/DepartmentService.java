package com.example.hwenv8.service;



import com.example.hwenv8.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    public Employee maxSalary(Integer department) {
        return employeeService.getAll().stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .max(Comparator.comparingInt(c -> c.getSalary())).get();

    }

    public Employee minSalary(Integer department) {
        return employeeService.getAll().stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .min(Comparator.comparingInt(c -> c.getSalary())).get();
        
    }

    public List<Employee> getEmployeeByDepartment(Integer department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .toList();
    }

    public Employee allEmployee() {
        final List<Employee> employeeList = employeeService.getAll();
        Map<Object, List<Employee>> streamEmployee = employeeList.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.toList()));
        return (Employee) streamEmployee;
    }
}
