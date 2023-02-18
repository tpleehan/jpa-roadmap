package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {

		// JPA는 EntityManagerFactory를 만들어야 하며, 하나만 생성해서 애플리케이션 전체에 공유한다.
		// 데이터베이스 하나 당 묶여서 돌아간다.
		// EntityManager는 쓰레드 간에 공유를 하면 안된다. 사용 후 꼭 버려야 한다.
		// JPA의 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
		// "hello"라는 건 persistence.xml 설정 파일의 persistence-unit name="hello" 읽어 온다.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		// DB Connection이라고 생각하면 쉽다. EntityManager를 통해 DB 작업을 하면 된다.
		EntityManager em = emf.createEntityManager();

		// JPA의 모든 데이터 변경은 Transaction 안에서 일어나야 한다.
		EntityTransaction tx = em.getTransaction();

		// transaction 시작
		tx.begin();

		// 필요한 로직 작성하기
		try {
			// Member findMember = em.find(Member.class, 1L);
			List<Member> result = em.createQuery("select m from Member as m", Member.class)
				.setFirstResult(5)
				.setMaxResults(8)
				.getResultList();

			for (Member member : result) {
				System.out.println("member.name = " + member.getName());
			}

			// jpa 저장
			// em.persist(member);
			// update query의 경우 em.persist를 호출하지 않아도 자동으로 값을 바꿔준다.

			// transaction commit (commit을 하지 않으면 반영이 안 된다.)
			tx.commit();
		} catch (Exception e) {
			// 문제가 생기면 rollback
			tx.rollback();
		} finally {
			// 자원을 다 사용하면 꼭 close를 통해 닫아줘야 한다.
			em.close();
		}
		// 전체 애플리케이션이 끝나면 EntityManagerFactory 닫기
		emf.close();
	}
}
