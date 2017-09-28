package util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	private static SqlSessionFactory factory;
	
	static {
		
		try {//放在静态方法下，factory只会被创建一次，进行初始化
			
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
			
		}catch(Exception e) {
			
		}finally {
			
			
		}
		
		
		
		
	}
	//静态方法
	public static SqlSession createSqlSession() {
		return factory.openSession();
	}
	public static void closeSqlSesion(SqlSession sqlSession) {
		if(null!=sqlSession) {
			
			sqlSession.close();
			
		}
	}
	
	

}
