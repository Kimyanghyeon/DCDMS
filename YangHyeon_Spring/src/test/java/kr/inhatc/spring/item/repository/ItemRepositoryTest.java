package kr.inhatc.spring.item.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.inhatc.spring.item.entity.Item;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class ItemRepositoryTest {

	@Autowired
	EntityManager em;

	@Autowired
	ItemRepository itemRepository;

	@Test
	@DisplayName("상품 저장 테스트")
	public void createItemTest() {
		Item item = new Item();
		item.setItemTitle("테스트 상품");
		item.setItemField("테스트 분야 ");
		item.setItemContents("테스트 상품 상세 설명");
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());

		Item savedItem = itemRepository.save(item);

		System.out.println(savedItem.toString());
	}// end of create Test

	public void createItemList() {
		for (int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setMember(null);
			item.setItemTitle("테스트 제목" + i);
			item.setItemField("테스트 분야" + i);
			item.setItemContents("테스트 내용" + i);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());

			Item savedItem = itemRepository.save(item);
		} // end of for
	}// end of createList Test

	@Test
	@DisplayName("제목 조회 테스트")
	public void findByItemTitleTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemTitle("테스트 제목1");

		for (Item item : itemList) {
			System.out.println(item.toString());
		} // end of for
	}// end of findByItemTitleTest

	@Test
	@DisplayName("제목 , 분야 조회 테스트 ")
	public void findByItemTitleOrItemField() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemTitleOrItemField("테스트 제목1", "테스트 분야2");
		for (Item item : itemList) {
			System.out.println(item.toString());
		} // end of for
	}// end of findByItemTitleOrItemField

}// end of class
