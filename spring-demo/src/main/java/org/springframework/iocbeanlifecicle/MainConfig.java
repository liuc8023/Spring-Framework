package org.springframework.iocbeanlifecicle;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * @Configuration可理解为用spring的时候xml里面的<beans>标签
 */
@Configuration
@ComponentScan(basePackages = "org.springframework.iocbeanlifecicle")
public class MainConfig {

	/**
	 * @Bean可理解为用spring的时候xml里面的<bean>标签
	 */
	@Bean
	public Person person(){
		Person person = new Person();
		person.setName("张三");
		person.setAge(30);
		return person;
	}
}
