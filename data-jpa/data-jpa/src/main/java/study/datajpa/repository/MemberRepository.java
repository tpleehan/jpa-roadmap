package study.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import study.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

	// @Query(name = "Member.findByUsername")
	// 관례: Member 엔티티에 있는 NamedQuery를 찾기 때문에 메서드명과 일치하면 따로 Query를 사용하지 않아도 된다.
	List<Member> findByUsername(@Param("username") String username);
}
