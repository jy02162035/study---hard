package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 功能描述：bean的生命周期
 *
 * <p>
 * 创建时间：2019/01/22 14:31:12
 * </p>
 * <p>
 * 作者：小D课堂
 * </p>
 */
public class main {

	@Autowired
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cycle.xml");

		Car car = (Car) ctx.getBean("car");
		
		System.out.println("***************" + car);
		Car car2 = (Car) ctx.getBean("car");
		System.out.println("***************" + car2);

		ctx.close();

	}

	// default Constructor...
	// set brand.... aodi1
	// postProcessBeforeInitialization... com.spring.Car@42607a4f car
	// initCar...
	// postProcessAfterInitialization... com.spring.Car@42607a4f car
	// com.spring.Car@42607a4f
	// com.spring.Car@42607a4f
	// destroyCar...

}
