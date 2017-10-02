package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Test;

import dao.RoleMapper;
import dao.UserMapper;
import pojo.Address;
import pojo.Role;
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
				user.setUserCode("测试");
				user.setUserName("ceshi001");
				user.setUserPassword("");
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
				sqlSession.getMapper(UserMapper.class).delete(user);
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
				MyBatisUtil.closeSqlSession(sqlSession);
			}
			
			
		}
		//
		@Test
		//不要忘了Test注解
		public void getUserListByRoleIdTest() {
			
			SqlSession sqlSession = null;
			try {
				
				List<User> userList = new ArrayList<User>();
				sqlSession  = MyBatisUtil.createSqlSession();
				Role role = new Role();
				role.setId(1);
				userList  = sqlSession.getMapper(UserMapper.class).getUserListByRoleId(role);
				sqlSession.commit();
				logger.debug("提交成功");
				for(User user:userList) {
					logger.debug("用户:"+user.getUserName());
					logger.debug("用户角色:"+user.getRoleName());
					logger.debug("用户代码:"+user.getUserCode());
					logger.debug("用户Id:"+user.getId());
					logger.debug("role对象id:"+user.getRole().getId());
					logger.debug("role对象代码:"+user.getRole().getRoleCode());
					logger.debug("role对象名称:"+user.getRole().getRoleName());
				}
				
				
			}catch(Exception e) {
				logger.debug("查询失败回滚");
				e.printStackTrace();
			}finally {
				MyBatisUtil.closeSqlSession(sqlSession);
				logger.debug("关闭会话");
			}
		}
		
		//
		@Test
		public void getAddressListByUserId() {
			SqlSession sqlSession = null;
			try {
				
				sqlSession = MyBatisUtil.createSqlSession();
				User user = new User();
				user.setId(1);
				User u2 = sqlSession.getMapper(UserMapper.class).getAddressListByUserId(user);
				sqlSession.commit();
				for(Address as:u2.getAddressList()) {
					logger.debug("用户名称:"+u2.getUserName());
					logger.debug("邮编地址:"+as.getAddressContent());
					logger.debug("邮编号码:"+as.getPostCode());
	
				}
				
				
			}catch(Exception e) {
				logger.debug("异常回滚");
				e.printStackTrace();
			}finally {
				logger.debug("关闭");
				MyBatisUtil.closeSqlSession(sqlSession);
			}
			
			
			
		}
		//使用动态SQL查询
		@Test 
		public void searchUserListTest() {
			SqlSession sqlSession = null;
			try {
				
				sqlSession = MyBatisUtil.createSqlSession();
				User user = new User();
				user.setRoleId(1);
				user.setUserCode("");
				user.setUserName("测");
				List<User> userList = sqlSession.getMapper(UserMapper.class).searchUserList(user);
				sqlSession.commit();
				for(User u:userList) {
					logger.debug("用户代码:"+u.getUserCode());
					logger.debug("用户名称:"+u.getUserName());
					logger.debug("用户权限:"+u.getRoleName());
				}
				
				
			}catch(Exception e) {
				logger.debug("异常回滚");
				sqlSession.rollback();
				e.printStackTrace();
			}finally {
				logger.debug("关闭");
				MyBatisUtil.closeSqlSession(sqlSession);
			}
			
			
			
		}
		
		//
		
		@Test
		public void userMapDepTest() {
			SqlSession sqlSession = null;
			try {
				
				sqlSession = MyBatisUtil.createSqlSession();
				String[] depIds = {"1","2"};
				List<User> userList = sqlSession.getMapper(UserMapper.class).getUserByDepId(depIds);
				
				sqlSession.commit();
				logger.debug("提交成功");
				for(User u:userList) {
					logger.debug("用户部门:"+u.getDepName());
					logger.debug("部门代码:"+u.getDepId());
					logger.debug("用户名称:"+u.getUserName());
					logger.debug("用户代码:"+u.getUserCode());
				}
				
				
				
			}catch(Exception e) {
				logger.debug("异常回滚");
				e.printStackTrace();
				sqlSession.rollback();
			}finally {
				logger.debug("关闭");
				MyBatisUtil.closeSqlSession(sqlSession);
				
			}
		}


}
