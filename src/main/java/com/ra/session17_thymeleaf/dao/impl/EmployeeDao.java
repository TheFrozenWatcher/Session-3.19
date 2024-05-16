package com.ra.session17_thymeleaf.dao.impl;

import com.ra.session17_thymeleaf.dao.IEmployeeDao;
import com.ra.session17_thymeleaf.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao implements IEmployeeDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> findAll()
    {
        Session session = sessionFactory.openSession();
        List<Employee> employeeList = null;
        try
        {
            employeeList = session.createQuery("from Employee").list();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return employeeList;
    }

    @Override
    public Employee findById(Integer id)
    {
        Session session = sessionFactory.openSession();
        Employee employee = null;
        try
        {
            employee = session.get(Employee.class, id);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return employee;
    }

    @Override
    public boolean save(Employee employee)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            if (employee.getId() == null)
            {
                session.save(employee);
            } else
            {
                session.update(employee);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally
        {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally
        {
            session.close();
        }
        return false;
    }
}
