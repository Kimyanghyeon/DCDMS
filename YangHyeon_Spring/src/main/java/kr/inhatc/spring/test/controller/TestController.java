package kr.inhatc.spring.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.inhatc.spring.test.dto.TestDto;

@RestController
public class TestController {
	
	@GetMapping(value="/hello")
	public String hello() {
		
		return "Hello World";
		
	}//end of hello
	
	
	@GetMapping(value = "/test")
	public TestDto test() {
		
		TestDto dto = new TestDto();
		dto.setName("김양현씨 지금 존나게 테스팅 중입니다");
		dto.setAge(20);
		return dto;
		
	}//end of TestDto

}//end of class
