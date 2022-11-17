package kr.inhatc.spring.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.inhatc.spring.member.constant.Role;
import kr.inhatc.spring.member.dto.MemberFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	@Column(name = "member_id")
	private Long Id; // 아이디(학번)

	@Column(unique = true)
	private String pw; // 비밀번호

	private String name; // 이름

	private char grade; // 반

	@Enumerated(EnumType.STRING)
	private Role role; // 역할 - 교수와 학생을 구분

	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

		Member member = new Member();
		member.setId(memberFormDto.getId());
		member.setName(memberFormDto.getName());
		member.setGrade(memberFormDto.getGrade());
		member.setRole(Role.STUDENT); // 기본값 : 학생
		String password = passwordEncoder.encode(memberFormDto.getPw());
		return null;

	}// end of createMember

}
// end of class
