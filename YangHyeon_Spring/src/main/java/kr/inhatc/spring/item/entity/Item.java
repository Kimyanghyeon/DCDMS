package kr.inhatc.spring.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import kr.inhatc.spring.member.entity.Member;
import kr.inhatc.spring.utils.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id; // 컨텐츠 코드

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member; // 작성자

	@Column(nullable = false)
	private String itemTitle; // 제목

	@Column(nullable = false)
	private String itemField; // 분야

	@Lob
	@Column(nullable = false)
	private String itemContents; // 내용

}// end of class
