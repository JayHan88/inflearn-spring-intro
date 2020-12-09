package me.yasuo.jay.repository;

import java.util.List;
import java.util.Optional;
import me.yasuo.jay.domain.Member;

public interface MemberRepository {

	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll();

}
