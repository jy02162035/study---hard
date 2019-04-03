package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.aop.Clac;

@RestController
public class AopController {

	@Autowired
	private Clac clac;

	@RequestMapping(value = "/div/{i}/{j}")
	public Object div(@PathVariable("i") int i, @PathVariable("j") int j) {
		return clac.div(i, j);
	}

	@RequestMapping(value = "/add/{i}/{j}")
	public Object add(@PathVariable int i, @PathVariable int j) {
		return clac.add(i, j);
	}
}
