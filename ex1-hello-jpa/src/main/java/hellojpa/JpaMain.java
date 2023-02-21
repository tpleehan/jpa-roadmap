package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

public class JpaMain {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {

			Member member1 = new Member();
			member1.setUsername("member1");
			em.persist(member1);

			em.flush();
			em.clear();

			Member refMember = em.getReference(Member.class, member1.getId());
			System.out.println("refMember = " + refMember.getClass()); // Proxy
			Hibernate.initialize(refMember); // 강제 초기화

			/*
			 프록시의 특징
			 - 프록시 객체는 처음 사용할 때 한 번만 초기화한다.
			 - 프록시 객체는 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것은 아님,
			   초기화되면 프록시 객체를 통해서 실제 엔티티에 접근 가능(교체 되는 것이 아님)
			 - 프록시 객체는 원본 엔티티를 상속받음, 따라서 타입 체크시 주의해야 한다.(== 비교 실패, 대신 instanceof 사용)
			 - 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티를 반환한다.
			   (이미 처음 proxy 조회하면 em.find를 해도 proxy 객체가 반환한다.)
			 - 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시 초기화하면 문제 발생
			   (하이버네이트는 org.hibernate.LazyInitializationException 예외를 발생 시킨다.)
			 */
			// Member findMember = em.getReference(Member.class, member1.getId());

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
