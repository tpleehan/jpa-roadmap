package jpabook.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	// lombok @Slf4j를 사용하면 따로 Logger를 선언하지 않아도 된다.
	// Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/")
	public String home() {
		log.info("home controller");
		return "home";
	}
	
}
