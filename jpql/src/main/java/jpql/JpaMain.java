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
			Team teamA = new Team();
			teamA.setName("팀A");
			em.persist(teamA);

			Team teamB = new Team();
			teamB.setName("팀B");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("회원1");
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("회원2");
			member2.setTeam(teamA);
			em.persist(member2);

			Member member3 = new Member();
			member3.setUsername("회원3");
			member3.setTeam(teamB);
			em.persist(member3);

			em.flush();
			em.clear();

			/*
			 페치 조인의 특징과 한계
			 - 페치 조인 대상에는 별칭을 줄 수 없다.
			  > 하이버네이트는 가능하지만, 가급적 사용하지 않는다.
			 - 둘 이상의 컬렉션은 페치 조인 할 수 없다.
			 - 컬렉션을 페치 조인하면 페이징 API(setFirstResult, setMaxResults)를 사용할 수 없다.
			  > 일대일, 다대일 같은 단일 값 연관 필드들은 페치 조인해도 페이징이 가능하다.
			  > 하이버네이트는 경고 로그를 남기고 메모리에서 페이징(매우 위험)
			 - 연관된 엔티티들을 SQL 한 번으로 조회 - 성능 최적화
			 - 엔티티에 직접 적용하는 글로벌 로딩 전략보다 우선으로 한다.
			  > @OneToMany(fetch = FetchType.LAZE) // 글로벌 로딩 전략
			 - 실무에서 글로벌 로딩 전략은 모두 지연 로딩
			 - 최적화가 필요한 곳은 페치 조인 적용
			 */
			String query = "select t from Team t";

			List<Team> result = em.createQuery(query, Team.class)
				.setFirstResult(0)
				.setMaxResults(2)
				.getResultList();

			System.out.println("result = " + result.size());

			for (Team team : result) {
				System.out.println("team = " + team.getName() + " | members = " + team.getMembers().size());
				for (Member member : team.getMembers()) {
					System.out.println("-> member = " + member);
				}
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
