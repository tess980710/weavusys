package com.weavusys.hrd.service;

import com.weavusys.hrd.entity.Employee;
import com.weavusys.hrd.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findByStatus(0);
    }

    public boolean save(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    public Employee modifyEmployee(String id, Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee user = existingEmployee.get();
            user.setId(employee.getId());
            user.setName(employee.getName());
            user.setEntryDate(employee.getEntryDate());
            user.setExitDate(employee.getExitDate());
            user.setEmployeeType(employee.getEmployeeType());
            user.setConversionDate(employee.getConversionDate());
            user.setRank(employee.getRank());
            return employeeRepository.save(user);
        }
        return null;
    }

    public boolean deleteById(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        optionalEmployee.ifPresent(employee -> {
            employee.setStatus(1);
            employeeRepository.save(employee);
        });
        return optionalEmployee.isPresent();
    }


    public Optional<Employee> findEmployee(String id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findByEntryDateYear(Integer year) {
        return employeeRepository.findByEntryDateYear(year);
    }
}
