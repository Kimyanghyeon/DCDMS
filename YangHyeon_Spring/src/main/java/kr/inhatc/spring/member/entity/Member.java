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
	private String Id; // 아이디(학번)

	@Column(unique = true)
	private String pw; // 비밀번호

	@Column(unique = true)
	private String email;

	private String name; // 이름

	private String grade; // 반

	@Enumerated(EnumType.STRING)
	private Role role; // 역할 - 교수와 학생을 구분

	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

		Member member = new Member();
		member.setId(memberFormDto.getId());
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setGrade(memberFormDto.getGrade());
		member.setRole(Role.STUDENT); // 기본값 : 학생
		String password = passwordEncoder.encode(memberFormDto.getPw());
		member.setPw(password);
		return member;

	}// end of createMember

}
// end of class
