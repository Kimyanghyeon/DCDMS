package kr.inhatc.spring.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.member.entity.Member;
import kr.inhatc.spring.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public Member saveMember(Member member) {

		validateDuplicate(member);
		return memberRepository.save(member);

	}// end of saveMember

	private void validateDuplicate(Member member) {
		Member findMember = memberRepository.findByPw(member.getPw());

		if (findMember != null) {
			throw new IllegalStateException("이미 등록된 사용자 입니다.\n학과 사무실에 문의해주세요 ");
		} // end of if

	}// end of validateDuplicate

}// end of class
