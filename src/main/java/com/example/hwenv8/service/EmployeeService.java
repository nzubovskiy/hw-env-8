package com.example.hwenv8.service;


import com.example.hwenv8.exceptions.EmployeeAlreadyAddedException;
import com.example.hwenv8.exceptions.EmployeeNotFoundException;
import com.example.hwenv8.exceptions.EmployeeStorageIsFullException;
import com.example.hwenv8.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private static final int LIMIT = 10;

    private final Map<Integer, Employee> employees = new HashMap<>(Map.of(
            1, new Employee ("Левина Кира Ярославовна", 2, 32_000),
            2, new Employee ("Александрова Виктория Анатольевна", 2, 37_000),
            3, new Employee ("Громов Тимур Максимович", 1, 37_000),
            4, new Employee ("Кравцова Мария Захаровна", 4, 34_000),
            5, new Employee ("Андреев Даниил Даниилович", 1, 34_000),
            6, new Employee ("Капустин Тимофей Константинович", 3, 38_000),
            7, new Employee ("Петров Александр Маркович", 4, 33_000),
            8, new Employee ("Олейникова Марта Кирилловна", 5, 35_000),
            9, new Employee ("Попов Максим Максимович", 5, 36_000),
            10, new Employee ("Боброва Анна Михайловна", 2, 36_000)));

    private String getKey(Employee employee) {
        return employee.getFullName();
    }

    public Employee add(String fullName, Integer department, Integer salary) {
        Employee employee = new Employee(fullName, department, salary);
        if (employees.containsKey(getKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(Integer.valueOf(getKey(employee)), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }


    public Employee remove(String fullName, Integer department, Integer salary) {
        String key = getKey(new Employee(fullName, department, salary));
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public Employee find(String fullName, Integer department, Integer salary) {
        Employee employee = new Employee(fullName, department, salary);
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();
            }
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

}





