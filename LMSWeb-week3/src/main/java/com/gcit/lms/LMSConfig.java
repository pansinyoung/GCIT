package com.gcit.lms;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.gcit.lms.dao.*;
import com.gcit.lms.service.*;
import com.gcit.lms.entity.*;

@Configuration
public class LMSConfig {
	
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost/library?useSSL=false";
	public static String password = "0131";
	public static String user = "root";
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		return ds;
	}
	
	@Bean
	public JdbcTemplate template(){
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public AllService allService() {
		return new AllService();
	}
	
	@Bean
	public AuthorDAO adao(){
		return new AuthorDAO();
	}
	
	@Bean
	public BookCopiesDAO bcdao(){
		return new BookCopiesDAO();
	}
	
	@Bean
	public BookDAO bdao(){
		return new BookDAO();
	}

	@Bean
	public BranchDAO brdao(){
		return new BranchDAO();
	}
	
	@Bean
	public BorrowerDAO bodao(){
		return new BorrowerDAO();
	}

	@Bean
	public LoanDAO ldao(){
		return new LoanDAO();
	}

	@Bean
	public PublisherDAO pdao(){
		return new PublisherDAO();
	}

	@Bean
	public GenreDAO gdao(){
		return new GenreDAO();
	}

	@Bean
	public SearchPagi searPag() {
		return new SearchPagi();
	}
}
