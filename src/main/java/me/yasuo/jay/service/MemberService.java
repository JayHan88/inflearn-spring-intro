package me.yasuo.jay.service;

import java.util.List;
import java.util.Optional;
import me.yasuo.jay.domain.Member;
import me.yasuo.jay.repository.MemberRepository;
import me.yasuo.jay.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class MemberService {

	private final MemberRepository memberRepository;

//	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// Join New Member
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
		                .ifPresent(m -> {
		                	throw new IllegalStateException("Name is already exist");
		                });
	}

	// Find All Members
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
