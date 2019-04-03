package testList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

/**
 * 
 * @author pengshun.wu
 *
 */
public class TestList {

	public static final int N = 50000;
	public static List values;

	static {
		Integer vals[] = new Integer[N];
		Random r = new Random();
		for (int i = 0, currval = 0; i < N; i++) {
			vals[i] = new Integer(currval);
			currval += r.nextInt(100) + 1;
		}
		values = Arrays.asList(vals);
	}

	static long searchTimeList(List lst) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			int index = Collections.binarySearch(lst, values.get(i));
			if (index != i)
				System.out.println("***错误***");
		}
		return System.currentTimeMillis() - start;
	}

	/**
	 * 二分查找(binary search)，比较列表是ArrayList和LinkedList时的查询速度
	 */
	@Test
	public void test1() {
		System.out.println("ArrayList消耗时间：" + searchTimeList(new ArrayList(values)));
		System.out.println("LinkedList消耗时间：" + searchTimeList(new LinkedList(values)));
	}

	static long addTimeList(List list) {
		long start = System.currentTimeMillis();
		Object o = new Object();
		for (int i = 0; i < N; i++)
			list.add(0, o);
		
		return System.currentTimeMillis() - start;
	}
	
	/**
	 * 二分查找(binary search)，比较列表是ArrayList和LinkedList时的查询速度
	 */
	@Test
	public void test2() {
		ArrayList arrayList = new ArrayList();
		arrayList.trimToSize();
		System.out.println("ArrayList消耗时间：" + addTimeList(new ArrayList()));
		System.out.println("LinkedList消耗时间：" + addTimeList(new LinkedList()));
	}

}