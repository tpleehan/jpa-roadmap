package hellojpa;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/*
 조인 전략
 - 장점
   - 테이블 정규화
   - 외래키 참조 무결성 제약조건 활용 가능
   - 저장공간 효율화
 - 단점
   - 조회시 조인을 많이 사용, 성능 저하(조인을 잘 활용하면 성능 저하 부분을 최적화 할 수 있다.)
   - 조회 쿼리가 복잡함
   - 데이터 저장시 insert SQL 2번 호출

 단일 테이블 전략
 - 장점
   - 조인이 필요 없으므로 일반적으로 조회 성능이 빠름
   - 조회 쿼리가 단순함
 - 단점
   - 자식 엔티티가 매핑한 컬럼은 모두 null 허용
   - 단일 테이블에 모든 것을 저장하므로 테이블이 커질 수 있고, 상황에 따라서 조회 성능이 오히려 느려질 수 있다.

 구현 클래스마다 테이블 전략(이 전략은 데이터베이스 설계자와 ORM 전문가 둘 다 추천X)
 - 장점
   - 서브 타입을 명확하게 구분해서 처리할 때 효과적
   - not null 제약조건 사용 가능
 - 단점
   - 여러 자식 테이블을 함께 조회할 때 성능이 느림(union sql 필요)
   - 자식 테이블을 통합해서 쿼리하기 어려움
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public class Item {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
