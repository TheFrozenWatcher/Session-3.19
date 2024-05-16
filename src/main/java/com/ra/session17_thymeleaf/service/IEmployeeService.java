package com.ra.session17_thymeleaf.service;

import com.ra.session17_thymeleaf.entity.Employee;

import java.util.List;

public interface IEmployeeService
{
    List<Employee> findAll();

    Employee findById(Integer id);

    boolean save(Employee employee);

    boolean delete(Integer id);
}
