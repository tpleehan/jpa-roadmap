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

			Member member = em.find(Member.class, 150L);
			member.setName("AAAAA");

			// 준영속 상태
			// 특정 엔티티만 준영속 상태로 전환
			// em.detach(member);
			// 영속성 컨텍스트를 완전히 초기화
			// em.clear();
			// 영속성 컨테스트 종료
			// em.close();

			Member member2 = em.find(Member.class, 150L);

			System.out.println("===============");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
