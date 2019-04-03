package spring.aop;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Clac {
	public Clac() {
		log.debug("******************Clac into constructor Clac()******************");
	}
	
	@PostConstruct
	public void init() {
		log.debug("******************Clac into init() By @PostConstruct******************");
	}

	public int add(int i, int j) {
		log.debug("******************Clac into add()******************");
		return i + j;
	}
	
	public int div(int i, int j) {
		log.debug("******************Clac into div()******************");
		return i/j;
	}
	
	@PreDestroy
	public void destory() {
		log.debug("******************Clac into destory() By @PreDestroy******************");
	}
}
