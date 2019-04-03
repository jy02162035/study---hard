package lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.Test;

import lambda.service.MyFun;
import lambda.service.UserService;

/**
 * () -> {} 左侧：Lambda表达式参数列表 右侧：Lambda表达式中所需执行的功能，即Lambda体 
 * 语法格式一：无参数无返回值 () -> System.out.println("Hello world"); 
 * 
 * 语法格式二：有参数无返回值 (x) -> System.out.println(x); 
 * 
 * 语法格式三：只有一个参数，小括号可以不写 x -> System.out.println(x);
 * 
 * 语法格式四：有两个以上参数，并有返回值，并Lambda体中有多条语句
 * 
 * 语法格式五: 只有一条语句，大括号和return都可以省略
 * 
 * 语法格式六：Lambda的参数类型可以省略不写，因为JVM编译器可以通过上下文推断出类型，即“类型推断”
 * 
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 * 
 * 二：Lambda表达式需要函数式接口的支持
 * 		函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可以使用直接@FunctionalInterface修饰
 * 
 * 
 * @author pengshun.wu
 *
 */
public class TestLambda {

	private String id;

	public String getId() {
		return "WPS100001";
	}

	public void setId(String id) {
		this.id = id;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int m = 5;
		// m -> m * 2;
		ExecutorService es = Executors.newCachedThreadPool();
		// 语法格式一：无参数无返回值 m不可以变更
		Runnable r1 = () -> System.out.println("Hello world" + m);
		// Runnable r2 = new Runnable() {
		// public void run() {
		// System.out.println("Hello world2");
		// }
		// };
		r1.run();
		// es.execute(r1);
		// es.execute(r2);

		Runnable r3 = TestLambda::staticHello;
		// Runnable r3 = () -> TestLambda.staticHello();

		// Callable<String> c1 = TestLambda::staticHelloWithResult;
		Callable<String> c1 = () -> TestLambda.staticHelloWithResult();
		Future<String> f1 = es.submit(c1);
		es.execute(r3);
		System.out.println(f1.get());

		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 3, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(20));

		Function<Integer, Integer> function = param -> {
			int sum = 0;
			for (int i = 1; i <= param; i++) {
				sum += i;
			}
			return sum;
		};
		System.out.println(function.apply(10));

		// 语法格式二：有参数无返回值
		UserService userService = (id) -> System.out.println(id);
		userService.getUser("wps12345");

		// 语法格式四
		Comparator<Integer> comparator = (x, y) -> {
			System.out.println("x == " + x);
			System.out.println("y == " + y);
			return Integer.compare(x, y);
		};
		System.out.println(comparator.compare(15, 10));

		// 语法格式五: 只有一条语句，大括号和return都可以省略
		Comparator<Integer> comparator2 = (x, y) -> Integer.compare(x, y);
		System.out.println(comparator2.compare(15, 10));

		// 语法格式六： 类型推断
		String[] strs = { "aa", "bb", "cc" }; // OK
		String[] strs2;
		// strs2 = {"aa", "bb", "cc"}; // NG
		List<Integer> list = new ArrayList<>();
		Comparator<Integer> comparator3 = (Integer x, Integer y) -> Integer.compare(x, y);
		System.out.println(comparator3.compare(15, 10));

		// 自定义函数式接口: 只有一个抽象方法的接口
		MyFun myFun1 = (x) -> x * x;
		System.out.println(myFun1.getValue(100));
		
		MyFun myFun2 = (x) -> x + 1000;
		System.out.println(myFun2.getValue(100));

	}

	// 自定义函数式接口: 只有一个抽象方法的接口
	@Test
	public void test() {
		MyFun myFun = (x) -> x + 1000;
		System.out.println(operation(100, myFun));
	}
	
	public Integer operation(Integer i, MyFun myFun){
		return myFun.getValue(i);
	}

	public void hello() {
		System.out.println("I am hello()");
	}

	public static void staticHello() {
		System.out.println("I am Static hello()");
	}

	public static String staticHelloWithResult() {
		return "Return From Static Hello()";
	}

}
