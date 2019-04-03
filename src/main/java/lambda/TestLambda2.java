package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;
/**
 * Java8 内置四大函数式接口使用汇总
 * 
 * Consumer<T> 消费型接口：传参消费，无返回值
 * 		提供方法 	void accept(T t);
 * 
 * Supplier<R>    供给型接口
 * 		提供方法 R get();
 * 
 * Function<T, R> 函数型接口
 * 		提供方法 R apply(T t);
 * 
 * Predicate<T>   断言型接口
 * 		提供方法  boolean test(T t);
 * 
 * 
 * @author pengshun.wu
 *
 */
public class TestLambda2 {
	
	/**
	 * 消费型接口
	 */
	@Test
	public void test1(){
		// 利用消费型接口花钱消费
		// 只消费参数，无返回值
		happy(1000, money -> System.out.println("消费型接口1：杨瑞喜欢大宝剑，每次消费 " + money + "元"));
	}
	
//	@Test
//	public void test1_1(){
//		Consumer<Double> consumer = money -> System.out.println("消费型接口2：张俊也喜欢大宝剑，每次消费 " + money + "元");
//		consumer.accept(2000.0);
//	}
	
	/**
	 * 拿着money去消费，至于怎么消费，在lambda里面实现
	 * @param money
	 * @param consumer
	 */
	public void happy(double money, Consumer<Double> consumer) {
		consumer.accept(money);
	}
//*****************************************************************************************************************************************************	
	/**
	 * 供给型接口
	 */
	@Test
	public void test2() {
		List<Integer> list = getNumList(10, ()-> (int)(Math.random() * 100));
		System.out.println("供给型接口返回：" + list);
	}
	
	/**
	 * 产生num个数并且返回，至于什么样的数在Lambda里面实现
	 * @param num
	 * @param supplier
	 * @return
	 */
	public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
		List<Integer> rList = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Integer radomInteger = supplier.get();
			rList.add(radomInteger);
		}
		return rList;
	}
//*****************************************************************************************************************************************************
	/**
	 * 函数式接口
	 */
	@Test
	public void test3() {
		String rlstString = strHanlder(" \t\t\t 王者荣耀耽误事       ", (str)-> (String)str.trim());
		System.out.println("函数式接口：" + rlstString);
		
		String rlstString2 = strHanlder("  wupengshun ", (str)-> (String)str.trim().toUpperCase());
		System.out.println("函数式接口：" + rlstString2);
	}
	
	/**
	 * 对str进行一些列处理并且返回，至于怎么样处理在Lambda里面实现
	 * @param str
	 * @param fun
	 * @return
	 */
	public String strHanlder(String str, Function<String, String> fun) {
		return fun.apply(str);
	}
	
	
//*****************************************************************************************************************************************************
	/**
	 * 断言型接口，过滤
	 */
	@Test
	public void test4(){
		List<String> list = Arrays.asList("wupengshun", "yangrui", "weikan", "aaa", "bbb", "ccc");
		List<String> afterFilterStrList = filterStr(list, str -> str.length() > 4);
		System.out.println("断言型接口：" + afterFilterStrList);
	}
	
	/**
	 * 对一个strList进行过滤，并且返回过滤后的list，至于怎么过滤，在Lambda里面实现
	 * @param strList
	 * @param pre
	 * @return
	 */
	public List<String> filterStr(List<String> strList, Predicate<String> pre) {
		List<String> rList = new ArrayList<>();
		for (String string : strList) {
			if (pre.test(string)) {
				rList.add(string);
			}
		}
		return rList;
	}
}
