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

			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("teamA");
			member.setAge(10);

			member.setTeam(team);

			em.persist(member);

			em.flush();
			em.clear();

			/*
			 JPA 서브쿼리 한계
			 - JPA는 WHERE, HAVING 절에서만 서브 쿼리 사용 가능
			 - SELECT 절도 가능(하이버네이트에서 지원)
			 - FROM 절의 서브 쿼리는 JPQL에서 불가능(현시점에서)
			   > 조인으로 풀 수 있으면 풀어서 해결
			 */
			String query = "select m from Member m left join Team t on m.username = t.name";
			List<Member> result = em.createQuery(query, Member.class)
				.getResultList();

			System.out.println("result = " + result.size());

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
