package hellojpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Member {

	@Id
	private Long id;

	// @Column에 name 속성을 지정하면 데이터베이스에서 사용되는 name이 된다.
	@Column(name = "name", nullable = false)
	private String username;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	// @Temporal에 TemporalType은 DATE, TIME, TIMESTAMP를 통해 데이터베이스에 날짜와 관련된 부분을 적절하게 지정할 수 있다.
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	private LocalDate testLocalDate;
	private LocalDateTime testLocalDateTime;

	@Lob
	private String description;

	// @Transient 데이터베이스가 아닌 메모리에 임시로 값을 보관하거나 사용할 때 선언한다.
	@Transient
	private int temp;

	public Member() {
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(final Integer age) {
		this.age = age;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(final RoleType roleType) {
		this.roleType = roleType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(final Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(final int temp) {
		this.temp = temp;
	}
}
