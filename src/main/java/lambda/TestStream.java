package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import lambda.entity.User;

/**
 * 强大的Stream 三个步骤： 1. 创建流 2. 中间操作 3. 终端操作
 * 
 * 关键字：惰性求值，延迟加载(filter--forEach)，短路(limit)，筛选或切片(filter,limit,skip,distinct)，映射，排序
 * @author pengshun.wu
 *
 */
public class TestStream {
	
	List<User> users = Arrays.asList(
			new User("yangrui", "杨瑞", 28, 5555d),
			new User("zhangjun", "张俊", 32, 6666d),
			new User("weikan", "魏侃", 25, 4444d),
			new User("wupengshun", "吴鹏顺", 33, 9999d),
			new User("wushenglei", "吴声雷", 27, 2222),
			new User("aaa", "王者荣耀", 33, 2222)
			);
	

	// 创建流
	@Test
	public void test1() {
		// 1. 可以通过Collection系列集合提供的stream()或并行流parallelStream()
		List<String> list = new ArrayList<String>();
		Stream stream1 = list.stream();
		// list.parallelStream();

		// 2. 可以通过Arrays中的静态方法stream()获取数组流
		Object[] objs = new Object[10];
		Stream stream2 = Arrays.stream(objs);

		// 3. 通过Stream中的of方法获取
		Stream<String> stream3 = Stream.of("aa", "bb", "cc");

		// 4. 创建无限流
		Stream.iterate(0, x -> x + 2)
//				.limit(10)
				// forEach接收一个Consumer接口(消费一个参数)
				.forEach(x -> System.out.println(x));

		// 5. Stream中的generate()
		// generate中接口一个suppliear接口，供给型接口，返回一个处理后的值
		Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
	}
	
	// 中间操作
	@Test
	public void test2() {
		// filter过滤：接收一个断言型接口predicate，传进去一个参数，返回一个判断结果boolean
		// die
		users.stream().filter((e)->{
			System.out.println("如果不进行forEach进行终止操作的话，中间操作不会执行，称之为惰性求值，也叫延迟加载");
			return  e.getAge() > 25;
		}).forEach((x) -> System.out.println(x));
		
	}
	
	// 短路（wps：按理说应该只打印满足过滤条件的，但是limit(3)时候，仍然打印了4次“短路”， 不太好用，再检证）
	@Test
	public void test3() {
		users.stream().filter((e)->{
			System.out.println("短路");
			return  e.getAge() > 25;
		}).limit(3).forEach((x) -> System.out.println(x));
		
	}
	
	// 映射
	@Test
	public void test4() {
		users.stream().filter(e -> e.getAge() > 30).map(e -> e.getName()).forEach(e -> System.out.println(e));
		// map里面对每条记录进行处理，并映射成一个新的元素
		users.stream().forEach(e -> System.out.println("old === " + e));
		users.stream().map((e) -> {
			e.setId(e.getId().toUpperCase());
			e.setName("BIG++++" + e.getName());
			return e;
		}).forEach(e -> System.out.println("new === " + e));
		 List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
		 Stream<Stream<Character>> stream = list.stream().map(e->filterCharater(e));
		 // {a, a}, {b, b,}, {c, c}, {d, d}
		 stream.forEach(sm->{
			 sm.forEach(s->{
				 System.out.println(s);
			 });
		 });
		 List<String> list2 = Arrays.asList("wps", "wk", "yr", "zj");
		// {w,p,s,w,k,y,r,z,j}
		 list2.stream().flatMap(e->filterCharater(e)).forEach(System.out::println);
		 
		 // 类似于add() addALL()
		 
		 
	}
	
	/**
	 * 排序
	 * 	sorted(): 自然排序(Comparable)
	 *  sorted(Comparator com): 定制排序
	 * @param str
	 * @return
	 */
	@Test
	public void test5() {
		List<String> list = Arrays.asList("wps", "wk", "yr", "zj", "abc", "def");
		list.stream().sorted().forEach(e -> System.out.println(e));

		users.stream().sorted((e1, e2) -> {
			if (e1.getAge() == e2.getAge()) {
				return e1.getId().compareTo(e2.getId());
			} else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
		}).forEach(System.out::println);

	}

	
	public Stream<Character> filterCharater(String str) {
		
		List<Character> list = new ArrayList<Character>();
		for (Character character : str.toCharArray()) {
			list.add(character);
		}
		return list.stream();
	}
	
	
}
