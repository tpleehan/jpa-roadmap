package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String city;
	private String street;
	private String zipcode;
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Order order;
}
