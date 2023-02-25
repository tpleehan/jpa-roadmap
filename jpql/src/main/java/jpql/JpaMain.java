package jpql;

import java.util.List;

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

			Member member1 = new Member();
			member1.setUsername("관리자");
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("관리자");
			em.persist(member2);

			em.flush();
			em.clear();

			String query = "select group_concat(m.username) from Member m";

			List<String> result = em.createQuery(query, String.class)
				.getResultList();

			for (String s : result) {
				System.out.println("s = " + s);
			}
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
