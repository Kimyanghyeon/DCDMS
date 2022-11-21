package kr.inhatc.spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 패스워드를 다시 확인하세요.");
		return "member/memberLogin";
	}// end of error

}// end of class
