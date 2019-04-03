package lambda;

import java.util.Optional;

import org.junit.Test;

import lambda.entity.Godness;
import lambda.entity.NewMan;
import lambda.entity.User;

/**
 * Optional 容器类的常用方法
 * @author 吴鹏顺
 *
 */
public class TestOptional {
	
	@Test
	public void test4 () {
		System.out.println(getGodnessName(Optional.ofNullable(null)));
	}
	
	String getGodnessName (Optional<NewMan> man) {
		return man.orElse(new NewMan())
				.getGodness()
				.orElse(new Godness("苍井空"))
				.getName();
	}
	
	@Test
	public void test3() {
		Optional<User> optional  = Optional.of(new User("wps", "wupengshun", 34, 8888.88));
		Optional<String> str = optional.map((e)-> e.getName());
		System.out.println(str.get());
		System.out.println(optional.get().getName());
	}
	
	@Test
	public void test2() {
		// return value == null ? empty() : of(value);
		Optional optional = Optional.ofNullable(new User()); 
		// 
		if (optional.isPresent()) {
			System.out.println("optional.isPresent() ==== " + optional.get());
		}
		
		if (Optional.ofNullable(null).isPresent()) {
			System.out.println(optional.get());
		} else {
			System.out.println("==================null");
		}
		
		System.out.println(Optional.ofNullable(null).orElse(new User("wps", "wupengshun", 35, 9999.99)));
	}

	
	@Test
	public void test1() {
		Optional optional = Optional.of(new User());
		System.out.println(optional.get());
		
		Optional optional2 = Optional.of(null); // 此时报空指针异常，构建的时候就报
		System.out.println(optional.get());
	}
}
