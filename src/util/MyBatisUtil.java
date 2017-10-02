package util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class MyBatisUtil {
	public static Logger logger = (Logger)LogManager.getLogger();
	private static SqlSessionFactory factory;
	
	static {
		
		try {//放在静态方法下，factory只会被创建一次，进行初始化
			
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
			logger.debug("factory:"+factory.getClass());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			
		}
		
		
		
		
	}
	//静态方法
	public static SqlSession createSqlSession() {
		logger.debug("factory:"+factory);
		return factory.openSession();
	}
	public static void closeSqlSession(SqlSession sqlSession) {
		if(null!=sqlSession) {
			
			sqlSession.close();
			
		}
	}

	

}
