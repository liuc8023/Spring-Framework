package org.springframework.iocbeanlifecicle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainStarter {
	public static void main(String[] args) {
		/**
		 * 使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文。
		 * 避免使用application.xml进行配置。相比XML配置，更加便捷。
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		Person person = (Person) context.getBean("person");
		System.out.println("person:"+person);
	}
}
