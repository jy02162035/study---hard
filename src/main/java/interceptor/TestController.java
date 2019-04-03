package interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//1. 在控制器方法执行之前, 执行的 preHandle 方法
//2. 执行控制器的action方法
//3. 执行完action, 解析view之前(如果有的话), 执行拦截器的 posthandle 方法
//4. 解析view
//5. 解析完之后, 执行 afterCompletion 方法
@RestController
public class TestController {

	// FirstInterceptor preHandle
	// SecondInterceptor preHandle
	// ThirdInterceptor preHandle
	// controller : FirstController index doing...
	// ThirdInterceptor postHandle
	// SecondInterceptor postHandle
	// FirstInterceptor postHandle
	// ThirdInterceptor afterCompletion
	// SecondInterceptor afterCompletion
	// FirstInterceptor afterCompletion
	@GetMapping("index")
	public String index() {
		System.out.println("controller : FirstController index doing...");
		return "1234";
	}
	
//	FirstInterceptor preHandle
//	SecondInterceptor preHandle
//	ThirdInterceptor preHandle
//	controller : FirstController index doing...
//	ThirdInterceptor afterCompletion
//	SecondInterceptor afterCompletion
//	FirstInterceptor afterCompletion
	@GetMapping("wrong")
	public String wrong() {
		System.out.println("controller : FirstController index doing...");
		int a = 1/0;
		return a + "1234";
	}
	
}
