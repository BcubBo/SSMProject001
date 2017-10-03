package com.bcubbo.dao;

import java.util.List;

import com.bcubbo.pojo.Employee;

public interface EmployeeMapper {
	
	//获取员工列表
	public List<Employee> getEmployeeList();

}
