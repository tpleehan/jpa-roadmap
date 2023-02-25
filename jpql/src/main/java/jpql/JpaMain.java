package jpql;

import java.util.Collection;
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
			em.persist(team);

			Member member1 = new Member();
			member1.setUsername("관리자");
			member1.setTeam(team);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("관리자");
			member2.setTeam(team);
			em.persist(member2);

			em.flush();
			em.clear();

			/*
			 경로 탐색을 사용한 묵시적 조인 시 주의사항
			 - 항상 내부 조인
			 - 컬렉션은 경로 탐색의 끝, 명시적 조인을 통해 별칭을 얻어야 한다.
			 - 경로 탐색은 주로 SELECT, WHERE 절에서 사용하지만 묵시적 조인으로 인해 SQL의 FROM(JOIN)절에 영향을 준다.

			 실무 조언
			 - 가급적 묵시적 조인 대신 명시적 조인을 사용한다.
			 - 조인은 SQL 튜닝에 중요 포인트이기 때문이다.
			 - 묵시적 조인은 조인이 일어나는 상황을 한눈에 파악하기 어렵다.
			 */
			String query = "select t.members from Team t";

			List<Collection> result = em.createQuery(query, Collection.class)
				.getResultList();

			System.out.println("result = " + result);

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
