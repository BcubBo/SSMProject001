package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Test;

import pojo.User;
import util.MyBatisUtil;

public class UserDaoTest {
	private Logger logger = (Logger)LogManager.getLogger();
	@Test
	public void countTest() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			sqlSession.selectOne("dao.UserMapper.count");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
			//关闭
		}
		
		
	}
	////
	
	
	@Test
	public void addTest() {
		
		SqlSession sqlSession = null;
		
		try {
			User user = new User();
			user.setUserCode("bcubbo");
			user.setUserName("bo");
			user.setUserPassword("bcubbo");
			sqlSession = MyBatisUtil.createSqlSession();
			sqlSession.insert("dao.UserMapper.add",user);
			sqlSession.commit();
		}catch(Exception e) {
			logger.debug("添加失败");
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			
			MyBatisUtil.closeSqlSession(sqlSession);
			logger.debug("添加成功关闭成功");
			
		
		}
	}
	
	@Test
	public void updateTest() {
		SqlSession sqlSession = null;
		
		try {
			User user = new User();
			user.setId(37);
			user.setUserCode("userCode001");
			user.setUserName("bcubbo0");
			user.setUserPassword("123455");
			sqlSession = MyBatisUtil.createSqlSession();
			sqlSession.update("dao.UserMapper.update",user);
			sqlSession.commit();
			logger.debug("update方法提交成功");
			
		} catch (Exception e) {
			logger.debug("update方法更新失败");
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
			logger.debug("update方法关闭成功");
			MyBatisUtil.closeSqlSession(sqlSession);
		}
	}
	
	@Test
	public void deleteTest() {
		SqlSession sqlSession = null;
		try {
			User user = new User();
			user.setId(40);
			sqlSession = MyBatisUtil.createSqlSession();
			sqlSession.delete("dao.UserMapper.delete",user);
			sqlSession.commit();
			logger.debug("delete提交成功");
		} catch (Exception e) {
			logger.debug("delete方法删除失败");
			sqlSession.rollback();
			e.printStackTrace();
			
		}finally {
			logger.debug("delete方法关闭成功");
			MyBatisUtil.closeSqlSession(sqlSession);
		}
	}
	@Test
	public void getUserListTest() {
		
		SqlSession sqlSession = null;
		
		
		try {
			List<User> userList = new ArrayList<User>();
			sqlSession = MyBatisUtil.createSqlSession();
			userList = sqlSession.selectList("dao.UserMapper.getUserList");
			sqlSession.commit();
			logger.debug("查询成功");
			for(User user:userList) {
				logger.debug("查询出的对象的属性值为:"+user.getUserCode());
			}
			
			
			
		} catch (Exception e) {
			logger.debug("全查询方法失败");
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			logger.debug("全查询方法关闭成功");
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		
		
	}

}
