package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import lambda.entity.User;

/**
 * 
 * @author pengshun.wu
 *
 */
public class MatchTest {
	List<User> users = Arrays.asList(
			new User("yangrui", "杨瑞", 28, 5555.22, Status.FREE),
			new User("zhangjun", "张俊", 32, 6666.66, Status.BUSY),
			new User("dongwangjuan", "董王娟", 35, 6666.66, Status.BUSY),
			new User("weikan", "魏侃", 25, 4444.44, Status.VACOTION),
			new User("wupengshun", "吴鹏顺", 33, 9999d, Status.FREE),
			new User("wushenglei", "吴声雷", 27, 2222.22, Status.BUSY),
			new User("aaa", "王者荣耀", 33, 2222.22, Status.VACOTION)
			);
	
	
	public enum Status {
		FREE, BUSY, VACOTION
	}
	
	@Test
	public void test8() {
		System.out.println(users.stream().map(User::getName).collect(Collectors.joining(",", "{{{{", "}}}}")));
	}
	
	/**
	 * 
	 */
	@Test
	public void test7() {
		
		DoubleSummaryStatistics collect = users.stream().collect(Collectors.summarizingDouble(User::getSlary));
		
		System.out.println(collect.getAverage());
		System.out.println(collect.getMax());
		System.out.println(collect.getMin());
		System.out.println(collect.getSum());
		System.out.println(collect.getCount());
	}
	
	/**
	 * 分区
	 */
	@Test
	public void test6() {
		Map<Boolean, List<User>> map = users.stream().collect(Collectors.partitioningBy((u) -> u.getSlary() > 5000d));
		System.out.println(map);
	}
	
	
	/**
	 * 多列分组，复合条件
	 */
	@Test
	public void test5() {
		Map<Enum, Map<String, List<User>>> map = users.stream().collect(Collectors.groupingBy(User::getStatus, Collectors.groupingBy((u)->{
			if (((User)u).getAge() <= 25) {
				return "少先队员";
			} else if (((User)u).getAge() <= 30) {
				return "知识愤青";
			} else {
				return "中年大牛";
			}
		})));
				
		System.out.println(map);
		
	}
	
	/**
	 * 分组
	 */
	@Test
	public void test4() {
		Map<Enum, List<User>> map = users.stream().collect(Collectors.groupingBy(User::getStatus));
		
		
		System.out.println(map);
	}
	
	/**
	 * 收集
	 */
	@Test
	public void test3() {

		Stream<String> strmName = users.stream().map(User::getName);
		strmName.forEach(System.out::println);
		System.out.println("**********************************");
		List<String> listIds = users.stream().map(User::getId).collect(Collectors.toList());
		listIds.forEach(System.out::println);
		System.out.println("**********************************");
		users.stream().map(User::getId).collect(Collectors.toCollection(() -> new LinkedList<String>())).forEach(System.out::println);
		System.out.println("**********************************");
		users.stream().map(User::getStatus).collect(Collectors.toCollection(HashSet::new)).forEach(System.out::println);
		
		// 总数
		Long count = users.stream().collect(Collectors.counting());
		// 平均值
		System.out.println("平均值 == " + users.stream().collect(Collectors.averagingDouble(User::getSlary)).doubleValue());
		// 薪水求和
		System.out.println("薪水求和 == " + users.stream().collect(Collectors.summingDouble(User::getSlary)).doubleValue());
		// 最大薪水的user
		System.out.println("最大薪水的user == " + users.stream().collect(Collectors.maxBy(Comparator.comparing(User::getSlary))).get());
		// 最小薪水
		System.out.println("最小薪水 == " + users.stream().map(User::getSlary).collect(Collectors.minBy(Double::compare)).get());
	}
	
	/**
	 * 规约reduce 可将流中元素反复结合起来，得到一个值
	 */
	@Test
	public void test2() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Integer sum = list.stream().reduce(100, (x, y) -> x + y);
		System.out.println(sum);

		List<String> strList = Arrays.asList("a", "b", "c");
		System.out.println(strList.stream().reduce("", (x, y) -> x + y));

		Optional oo = users.stream().map(User::getSlary).reduce(Double::sum);
		System.out.println(oo.get());

		Optional ooo = users.stream().map(x -> x.getSlary()).reduce((x, y) -> x + y);
		System.out.println(ooo.get());

	}
	
	/**
	 * 查找与匹配
	 * Stream的终止操作
	 * allMatch：检查元素是否全部匹配
	 * anyMatch：检查元素至少有一个匹配
	 * noneMatch： 检查是否没有匹配全部元素
	 * findFirst：返回第一个元素
	 * findAny：返回当前流中任意元素
	 * count：返回流中元素个数
	 * max：返回流中最大值
	 * min：返回流中最小值
	 */
	@Test
	public void test1() {
		System.out.println("ALL Status.BUSY == " + users.stream().allMatch(x->x.getStatus().equals(Status.BUSY)));
		System.out.println("HAVE Status.BUSY == " + users.stream().anyMatch(x->x.getStatus().equals(Status.BUSY)));
		System.out.println("NONE Status.BUSY == " + users.stream().noneMatch(x->x.getStatus().equals(Status.BUSY)));
		Optional o = users.stream().sorted((e1, e2)-> Integer.compare(e1.getAge(), e2.getAge())).findFirst();
		System.out.println("年龄最小的user是 " + o.get());
		
		System.out.println("工资最多user是 " + users.stream().max((d1, d2)->Double.compare(d1.getSlary(), d2.getSlary())).get());
		
		System.out.println("最少工资是 " + users.stream().min(Comparator.comparing(User::getSlary)).map(User::getSlary));
		System.out.println("使用方法引用求得最少工资是 " + users.stream().map(User::getSlary).min(Double::compare));
	}
}
