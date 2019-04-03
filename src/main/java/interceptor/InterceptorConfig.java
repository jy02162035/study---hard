package interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new FirstInterceptor());
		registry.addInterceptor(new SecondInterceptor());
		registry.addInterceptor(new ThirdInterceptor());
		registry.addInterceptor(new LoginInterceptor());
		super.addInterceptors(registry);
	}

}
