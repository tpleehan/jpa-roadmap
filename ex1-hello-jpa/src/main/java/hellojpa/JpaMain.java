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
			member.setName("ZZZZZ");

			// entity의 값만 변경하면 무조건 update query가 실행되고, 트랜잭션 커밋되는 시점에 변경을 반영한다.
			// 값을 변경하는 경우에는 persist 또는 update와 같이 호출하지 않는다.
			// em.persist(member);

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
