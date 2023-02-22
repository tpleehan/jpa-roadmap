package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
			Address address = new Address("city", "street", "10");

			Member member = new Member();
			member.setUsername("member1");
			member.setHomeAddress(address);
			em.persist(member);

			Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
			member.setHomeAddress(newAddress);

			/*
			 객테 타입의 한계
			 - 항상 값을 복사해서 사용하면 공유 참조로 인해 발생하는 부작용을 피할 수 있다.
			 - 문제는 임베디드 타입처럼 직접 정의한 값 타입은 자바의 기본타입이 아니라 객체 타입이다.
			 - 자바 기본 타입에 값을 대입하면 값을 복사한다.
			 - 객체 타입은 참조 값을 직접 대입하는 것을 막을 방법이 없다.
			 - 객체의 공유 참조는 피할 수 없다.
			 */
			/*
			 불변 객체
			 - 객체 타입을 수정할 수 없게 만들면 부작용을 원천 차단
			 - 값 타입은 불변 객체(immutable object)로 설계 해야한다.
			 - 불변 객체: 생성 시점 이후 절대 값을 변경할 수 없는 객체
			 - 생성자로만 값을 설정하고 수정자(Setter)를 만들지 않으면 된다.
			 - 참고: Integer, String은 자바가 제공하는 대표적인 불변 객체
			 */

			Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setHomeAddress(copyAddress);
			em.persist(member2);

			// 임베디드 타입 같은 값 타입을 여러 엔티티에서 공유하면 위험하다.
			// 부작용(side-effect) 발생
			member.getHomeAddress().setCity("newCity");

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}
}
