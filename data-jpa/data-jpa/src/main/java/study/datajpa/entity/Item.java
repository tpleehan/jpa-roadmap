package study.datajpa.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {

	/*
	 * @GenerateValue를 사용하지 않을 경우 save() 호출 시점에 merge()가 호출된다.
	 * Persistable을 사용해서 새로운 엔티티 확인 여부를 직접 구현할 수 있는 방법이 있다.
	 * 대체로 @CreatedDate를 이용해서 새로운 엔티티 여부를 편리하게 확인해서 사용할 수 있다. (@CreatedDate 값이 없으면 새로운 엔티티로 판단한다.)
	 */

	@Id
	private String id;

	@CreatedDate
	private LocalDateTime createdDate;

	public Item(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return createdDate == null;
	}
}
