package com.ra.session17_thymeleaf.service.impl;

import com.ra.session17_thymeleaf.dao.IEmployeeDao;
import com.ra.session17_thymeleaf.entity.Employee;
import com.ra.session17_thymeleaf.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService
{
    private final IEmployeeDao employeeDao;

    @Autowired
    public EmployeeService(IEmployeeDao employeeDao)
    {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll()
    {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(Integer id)
    {
        return employeeDao.findById(id);
    }

    @Override
    public boolean save(Employee employee)
    {
        return employeeDao.save(employee);
    }

    @Override
    public boolean delete(Integer id)
    {
        return employeeDao.delete(id);
    }
}
