package com.bcubbo.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bcubbo.pojo.Employee;

public class EmployeeDaoTest {
	private SqlSessionTemplate sqlSessionTemplate;
	protected ApplicationContext ctx = null;
	private EmployeeDaoTest test;
	private Logger logger = (Logger)LogManager.getLogger();
	//springMVC使用Model或者ModelAndView进行前台到后台的映射
	@Before
	public void setUp() throws Exception {

		ctx = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		test = (EmployeeDaoTest)ctx.getBean("employeeDaoTest");
		//获取bean
	}
	
	@Test
	public void getEmployeeListTest() {
		
		List<Employee> employeeList = test.sqlSessionTemplate.selectList("com.bcubbo.dao.EmployeeMapper.getEmployeeList");
		for(Employee em:employeeList) {
			
			logger.debug("员工信息:"+em.getName());
			logger.debug("员工代码:"+em.getId());
			
		}
		logger.debug("**********查询结束**********");
	}
	
	
	
	
	
	
	
	
//setter&getter

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
}
