package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Test;

import pojo.User;

import org.junit.Assert;

public class UserTest {
	private Logger logger = (Logger)LogManager.getLogger();
	@Test
	public void countTest() {
		
		String resourceString = "mybatis-config.xml";
		int count  = 0;
		SqlSession sqlSession = null;
		
		try {
			//1 读取配置文件(mybatis-config.xml)的输入流
			InputStream is = Resources.getResourceAsStream(resourceString);
			//2创建sqlSessionFactory对象，完成对配置文件的读取
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			//创建sqlSessionFactory对象
			//3创建sqlSession对象
			sqlSession = factory.openSession();//此部获取对象
			//4调用mapper文件进行数据操作（调用之前必须要把mapper文件加入到mybatis-config.xml文件中);
			count = sqlSession.selectOne("dao.UserMapper.count");//返回的是integer型的数
			logger.info("countTest总数为:"+count);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
			//相当于request的级别，所以要谨记关闭
		}
		
		Assert.assertEquals(17, count);
		
		
		
	}
	////
	
	
	@Test
	public void addTest() {
		String resource = "mybatis-config.xml";
		SqlSession sqlSession = null;
		
		try {
			User user = new User();
			user.setUserCode("bcubbo");
			user.setUserName("bo");
			user.setUserPassword("bcubbo");
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(is);
			sqlSession = factory.openSession();
			sqlSession.insert("dao.UserMapper.add",user);
			sqlSession.commit();
		}catch(Exception e) {
			logger.debug("添加失败");
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			
			sqlSession.close();
			logger.debug("添加成功关闭成功");
			
		
		}
	}

}
