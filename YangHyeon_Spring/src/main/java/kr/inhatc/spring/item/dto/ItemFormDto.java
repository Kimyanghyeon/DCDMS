package kr.inhatc.spring.item.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import kr.inhatc.spring.item.constant.LanguageType;
import kr.inhatc.spring.item.entity.Item;
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
	private LanguageType itemField; // 분야

	@Lob
	@Column(nullable = false)
	private String itemContents; // 내용

	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

	private List<Long> itemImgIds = new ArrayList<>();

	private static ModelMapper modelMapper = new ModelMapper();

	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}// end of create

	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}// end of ItemFormDto

}// end of class
