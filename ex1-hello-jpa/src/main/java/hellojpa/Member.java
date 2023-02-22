package hellojpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	/*
	 임베디드 타입과 테이블 매핑
	 - 임베디드 타입은 엔티티의 값일 뿐이다.
	 - 임베디드 타입을 사용하기 전과 후에 `매핑하는 테이블은 같다.`
	 - 객체와 테이블을 아주 세밀하게(find-grained) 매핑하는 것이 가능
	 - 잘 설계한 ORM 애플리케이션은 매핑한 테이블의 수보다 클래스의 수가 더 많다.
	 */

	// 기간 Period
	@Embedded
	private Period workPeriod;

	// 주소 address
	@Embedded
	private Address homeAddress;

	/*
	 @AttributeOverride: 속성 재정의
	 - 한 엔티티에서 같은 값 타입을 사용하면, 컬럼명이 중복된다.(@AttributeOverride를 사용하지 않는 경우)
	 - @AttributeOverrides, @AttributeOverride를 사용하면 컬럼명 속성을 재정의 할 수 있다.
	 */
	// 주소 work_address
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "city",
			column = @Column(name = "WORK_CITY")),
		@AttributeOverride(name = "street",
			column = @Column(name = "WORK_STREET")),
		@AttributeOverride(name = "zipcode",
			column = @Column(name = "WORK_ZIPCODE"))
	})
	private Address workAddress;

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

	public Period getWorkPeriod() {
		return workPeriod;
	}

	public void setWorkPeriod(Period workPeriod) {
		this.workPeriod = workPeriod;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
}
