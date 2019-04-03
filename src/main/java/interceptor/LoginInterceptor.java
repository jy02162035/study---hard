package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
		HandlerMethod method = (HandlerMethod) handle;
		Class<?> controllerType = method.getBeanType();

		if (method.getMethodAnnotation(NoLogin.class) != null || controllerType.getAnnotation(NoLogin.class) != null) {
			return true;
		}

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("token");
		if (!StringUtils.isEmpty(token)) {
			return true;
		}

		response.sendRedirect("/login/index");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}

}
