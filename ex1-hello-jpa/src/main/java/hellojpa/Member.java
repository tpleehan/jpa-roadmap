package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	/*
	 프록시와 즉시로딩 주의
	 - 가급적 지연 로딩만 사용(특히 실무에서)
	 - 즉시 로딩을 적용하면 예상하지 못한 SQL이 발생한다.
	 - 즉시 로딩은 JPQL에서 N+1 문제를 일으킨다.
	 - @ManyToOne, @OneToOne은 기본이 즉시 로딩 -> LAZY로 설정
	 - @OneToMany, @ManyToMany는 기본이 지연로딩
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Team team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
