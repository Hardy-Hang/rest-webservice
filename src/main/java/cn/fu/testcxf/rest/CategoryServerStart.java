package cn.fu.testcxf.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CategoryServerStart {
	 org.apache.cxf.interceptor.LoggingInInterceptor  inter = null;
	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		CategoryService categoryService = (CategoryService) appContext
				.getBean("categoryService");
		// Service instance
		JAXRSServerFactoryBean restServer = new JAXRSServerFactoryBean();
		restServer.setResourceClasses(Category.class);
		restServer.setServiceBean(categoryService);
		restServer.setAddress("http://localhost:9000/");
		Server server = restServer.create();
		server.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			br.readLine();
		} catch (IOException e) {
		}
		System.out.println("Server Stopped");
		System.exit(0);
	}
}