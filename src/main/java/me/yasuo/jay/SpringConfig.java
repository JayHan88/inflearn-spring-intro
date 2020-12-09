package me.yasuo.jay;

import javax.sql.DataSource;
import me.yasuo.jay.repository.JdbcTemplateMemberRepository;
import me.yasuo.jay.repository.MemberRepository;
import me.yasuo.jay.repository.MemoryMemberRepository;
import me.yasuo.jay.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	private final DataSource dataSource;

	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public MemberRepository memberRepository() {
		return new JdbcTemplateMemberRepository(dataSource);
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

}
