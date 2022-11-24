package kr.inhatc.spring.item.dto;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemFormDto {

	private Long id; // 컨텐츠 코드

	@NotBlank(message = "제목은 필수 입력입니다.")
	private String itemTitle; // 제목

	@NotBlank(message = "분야는 필수 입력입니다.")
	private String itemField; // 분야

	@Lob
	@Column(nullable = false)
	private String itemContents; // 내용

	public void updateItem(ItemFormDto itemFormDto) {
	}// end of updateItem

}// end of calss
