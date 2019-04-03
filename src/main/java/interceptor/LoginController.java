package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@NoLogin
@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	private String controllerPath = "login/";

	// @NoLogin
	@GetMapping("index")
	public String index() {
		HttpSession session = request.getSession();
		session.setAttribute("token", "token");
		return controllerPath + "index";
	}

	// @NoLogin
	@PostMapping("checkOut")
	@ResponseBody
	public String checkOut() {
		HttpSession session = request.getSession();
		session.setAttribute("token", null);

		return "ok";
	}
}