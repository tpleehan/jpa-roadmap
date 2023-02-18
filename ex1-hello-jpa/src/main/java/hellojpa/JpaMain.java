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

			// 비영속
			Member member = new Member();
			member.setId(100L);
			member.setName("HelloJPA");

			System.out.println("==== BEFORE ====");
			// 객체를 저장한 상태(영속)
			em.persist(member);

			// 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
			em.detach(member);
			// 객체를 삭제한 상태(삭제)
			em.remove(member);
			System.out.println("==== AFTER ====");

			tx.commit();
		} catch (Exception e) {
			// 문제가 생기면 rollback
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
