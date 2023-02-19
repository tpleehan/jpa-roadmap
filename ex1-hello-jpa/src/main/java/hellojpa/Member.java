package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// @Table 속성
// name: 매핑할 테이블 이름 (name을 따로 작성하지 않으면 기본 값으로 엔티티 이름을 사용한다.)
// catalog: 데이터베이스 catalog 매핑
// schema: 데이터베이스 schema 매핑
// uniqueConstraints: DDL 생성 시 유니크 제약 조건 생성
@Table(name = "MBR")
public class Member {

	@Id
	private Long id;
	private String name;

	public Member() {
	}

	public Member(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
