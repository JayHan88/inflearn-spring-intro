package me.yasuo.jay.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import me.yasuo.jay.domain.Member;
import me.yasuo.jay.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

	MemoryMemberRepository memberRepository;
	MemberService memberService;

	@BeforeEach
	void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("Jay");

		//when
		Long saveId = memberService.join(member);

		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("Jay");

		Member member2 = new Member();
		member2.setName("Jay");

		//when
		memberService.join(member1);

		//then
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("Name is already exist");
	}

	@Test
	void findMembers() {
	}

	@Test
	void findOne() {
	}
}