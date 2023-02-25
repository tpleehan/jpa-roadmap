package jpql;

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
			Team teamA = new Team();
			teamA.setName("팀A");
			em.persist(teamA);

			Team teamB = new Team();
			teamB.setName("팀B");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("회원1");
			member1.setAge(0);
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("회원2");
			member2.setAge(0);
			member2.setTeam(teamA);
			em.persist(member2);

			Member member3 = new Member();
			member3.setUsername("회원3");
			member3.setAge(0);
			member3.setTeam(teamB);
			em.persist(member3);

			/*
			 벌크 연산 주의
			 - 벌크 연산은 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리한다.
			  > 벌크 연산을 먼저 실행
			  > 벌크 연산 수행 후 영속성 컨텍스트 초기화
			 */
			// flush 자동 호출 (auto mode 일 때는 - comit, query 시점에 flush 호출)
			int resultCount = em.createQuery("update Member m set m.age = 20")
				.executeUpdate();

			em.clear();

			Member findMember = em.find(Member.class, member1.getId());
			System.out.println("findMember = " + findMember.getAge());

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
