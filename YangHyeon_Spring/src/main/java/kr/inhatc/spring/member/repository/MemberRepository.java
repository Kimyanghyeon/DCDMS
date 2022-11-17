package kr.inhatc.spring.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByPw(String pw);

}// end of interface
