package kr.inhatc.spring.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberFormDto {

	private Long id; // 아이디(학번)

	private String pw; // 비밀번호

	private String name; // 이름

	private char grade; // 반

}// end of class
