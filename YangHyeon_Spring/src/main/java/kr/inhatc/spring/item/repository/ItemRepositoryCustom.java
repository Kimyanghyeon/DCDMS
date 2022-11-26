package kr.inhatc.spring.item.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.inhatc.spring.item.dto.ItemSearchDto;
import kr.inhatc.spring.item.dto.MainItemDto;
import kr.inhatc.spring.item.entity.Item;

public interface ItemRepositoryCustom {

	Page<Item> getStudentItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

	Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}// end of interface
