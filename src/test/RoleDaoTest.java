package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Test;

import dao.RoleMapper;
import pojo.Role;
import util.MyBatisUtil;

public class RoleDaoTest {

	
	private Logger logger = (Logger)LogManager.getLogger();
	
	
	@Test
	public void addTest() {
		
		SqlSession sqlSession = null;
		
		
		try {
			Role role = new Role();
			role.setRoleCode("ADMIN1");
			role.setRoleName("系统管理1");
			sqlSession = MyBatisUtil.createSqlSession();
			sqlSession.getMapper(RoleMapper.class).add(role);
			logger.debug("添加结束");
			sqlSession.commit();
			logger.debug("提交完成");
		} catch (Exception e) {
			logger.debug("添加角色失败回滚");
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
			logger.debug("关闭会话");
		}
		
		
	}
	
	
	@Test
	public void updateTest() {
		SqlSession sqlSession = null;
		try {
			Role role = new Role();
			role.setId(6);
			role.setRoleCode("MANAGER");
			role.setRoleName("经理");
			sqlSession = MyBatisUtil.createSqlSession();
			sqlSession.getMapper(RoleMapper.class).update(role);
			logger.debug("更新结束");
			sqlSession.commit();
			logger.debug("提交完成");
		} catch (Exception e) {
			logger.debug("更新角色失败回滚");
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
			logger.debug("更新方法关闭会话");
		}		
		
		
	}
	
	
	@Test
	public void deleteTest() {
		SqlSession sqlSession = null;
		try {
			Role role = new Role();
			role.setId(6);
			sqlSession = MyBatisUtil.createSqlSession();
			sqlSession.getMapper(RoleMapper.class).delete(role);
			logger.debug("删除结束");
			sqlSession.commit();
			logger.debug("提交完成");
		} catch (Exception e) {
			logger.debug("删除角色失败回滚");
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
			logger.debug("删除方法关闭会话");
		}
		
		
		
	}
	
	
	@Test
	public void getRoleListTest2() {
		List<Role> roleList = new ArrayList<Role>();
		SqlSession sqlSession = null;
		try {
			Role role = new Role();
			role.setRoleCode("ADMIN1");
			role.setRoleName("系统管理1");
			sqlSession = MyBatisUtil.createSqlSession();
			roleList = sqlSession.getMapper(RoleMapper.class).getRoleList(role);
			//sqlSession.selectList("dao.RoleMapper.getRoleList");
			logger.debug("查询结束");
			for(Role rol:roleList) {
				logger.debug("返回结果的角色名称为:"+rol.getRoleName());
			}
			
			sqlSession.commit();
			logger.debug("提交完成");
		} catch (Exception e) {
			logger.debug("添加角色失败回滚");
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
			logger.debug("关闭会话");
		}
		
		
	}
	
	//
	@Test
	public void getRoleList() {
		
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			Role role = new Role();
			role.setRoleCode("A");
			role.setRoleName("财");
			List<Role> roleList = sqlSession.getMapper(RoleMapper.class).getRoleList(role);
			sqlSession.commit();
			logger.debug("提交成功");
			for(Role r:roleList) {
				logger.debug("身份代码:"+r.getRoleCode());
				logger.debug("身份名称:"+r.getRoleName());
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

}
