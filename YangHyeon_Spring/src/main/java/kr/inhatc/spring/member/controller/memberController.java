package kr.inhatc.spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class memberController {

	@GetMapping("/login")
	public String login() {
		return "member/memberLogin";

	}// end of login

}// end of class
