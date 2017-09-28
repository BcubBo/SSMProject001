package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Test;

import dao.UserMapper;
import pojo.User;
import util.MyBatisUtil;

public class UserDaoTestByMapperInterface {
		private Logger logger = (Logger)LogManager.getLogger();
		@Test
		public void countTest() {
			SqlSession sqlSession = null;
			int count = 0;
			try {
				sqlSession = MyBatisUtil.createSqlSession();
				count = sqlSession.getMapper(UserMapper.class).count();
				//通过接口的类型进行了UserMapper文件的映射关系
				logger.debug("总计:"+count);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				MyBatisUtil.closeSqlSesion(sqlSession);
				//关闭
			}
			
			
		}
		////
		
		
		@Test
		public void addTest() {
			
			SqlSession sqlSession = null;
			
			try {
				User user = new User();
				user.setUserCode("bcubbo1");
				user.setUserName("bo1");
				user.setUserPassword("bcubbo1");
				sqlSession = MyBatisUtil.createSqlSession();
				sqlSession.getMapper(UserMapper.class).add(user);
				sqlSession.commit();
			}catch(Exception e) {
				logger.debug("添加失败");
				e.printStackTrace();
				sqlSession.rollback();
			}finally {
				
				MyBatisUtil.closeSqlSesion(sqlSession);
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
				sqlSession.getMapper(UserMapper.class).update(user);
				sqlSession.commit();
				logger.debug("update方法提交成功");
				
			} catch (Exception e) {
				logger.debug("update方法更新失败");
				e.printStackTrace();
				sqlSession.rollback();
			}finally {
				logger.debug("update方法关闭成功");
				MyBatisUtil.closeSqlSesion(sqlSession);
			}
		}
		
		@Test
		public void deleteTest() {
			SqlSession sqlSession = null;
			try {
				User user = new User();
				user.setId(40);
				sqlSession = MyBatisUtil.createSqlSession();
				sqlSession.getMapper(UserMapper.class).delete(user);
				sqlSession.commit();
				logger.debug("delete提交成功");
			} catch (Exception e) {
				logger.debug("delete方法删除失败");
				sqlSession.rollback();
				e.printStackTrace();
				
			}finally {
				logger.debug("delete方法关闭成功");
				MyBatisUtil.closeSqlSesion(sqlSession);
			}
		}
		@Test
		public void getUserListTest() {
			
			SqlSession sqlSession = null;
			
			
			try {
				List<User> userList = new ArrayList<User>();
				sqlSession = MyBatisUtil.createSqlSession();
				userList = sqlSession.getMapper(UserMapper.class).getUserList();
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
				MyBatisUtil.closeSqlSesion(sqlSession);
			}
			
			
		}


}
