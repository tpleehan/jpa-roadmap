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

			Member member = new Member(200L, "member200");
			em.persist(member);

			// 플러시는 영속성 컨텍스트를 비우지 않는다.
			// 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화한다.
			// 트랜잭션이라는 작업 단위가 중요하며, 커밋 직전에만 동기화 하면 된다.
			// em.setFlushMode(FlushModeType.COMMIT, AUTO) 모드 옵션을 바꿀 수 있지만 Auto 기본값 사용하는 것을 권장
			em.flush();

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
