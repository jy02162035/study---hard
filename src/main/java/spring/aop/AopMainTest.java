package spring.aop;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("restriction")
public class AopMainTest{
	public static ClassPathXmlApplicationContext context = null;
	
//	private static final long valueOffset;
//	
//	private static final sun.misc.Unsafe unsafe;
//	
//	static {
//		unsafe = sun.misc.Unsafe.getUnsafe();
//	}
//	
//	 static
//	  {
//	    try
//	    {
//	    	valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
//	    	System.out.println("valueOffset === " + valueOffset);
//	    }
//	    catch (Exception localException)
//	    {
//	      throw new Error(localException);
//	    }
//	  }
	
	
//	@BeforeClass
	public static void getContext() {
		context = new ClassPathXmlApplicationContext("spring-cycle.xml");
	}
	
	@Test
	public void doAdd() {
		Clac clac = (Clac) context.getBean("clac");
		System.out.println("AopMainTest.doAdd()----->>>>> " + clac.add(10, 5));
	}
	
	@Test
	public void testAtomicInteger() {
		AtomicInteger atomicInteger = new AtomicInteger(10);
		System.out.println(atomicInteger.get());
		atomicInteger.set(20);
		System.out.println(atomicInteger);
		System.out.println(atomicInteger.incrementAndGet());
		System.out.println(atomicInteger.incrementAndGet());
		System.out.println(atomicInteger.incrementAndGet());
		
		
		
	}
	
	@Test
	public void doDiv() {
		Clac clac = (Clac) context.getBean("clac");
		System.out.println("AopMainTest.doDiv()----->>>>> " + clac.div(10, 2));
	}
	
}
