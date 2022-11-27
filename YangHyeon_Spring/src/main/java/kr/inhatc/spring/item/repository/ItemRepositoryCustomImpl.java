package kr.inhatc.spring.item.repository;

import static kr.inhatc.spring.item.entity.QItem.item;
import static kr.inhatc.spring.item.entity.QItemImg.itemImg;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.inhatc.spring.item.constant.LanguageType;
import kr.inhatc.spring.item.dto.ItemSearchDto;
import kr.inhatc.spring.item.dto.MainItemDto;
import kr.inhatc.spring.item.dto.QMainItemDto;
import kr.inhatc.spring.item.entity.Item;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

	private JPAQueryFactory queryFactory; // 동적으로 쿼리 생성을 위해 사용

	public ItemRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em); // 초기화
	}// end of ItemRepositoryCustomImpl

	private BooleanExpression searchLanguageTypeEq(LanguageType searchLanguageType) {
		return searchLanguageType == null ? null : item.itemField.eq(searchLanguageType);
	}// end of searchLanguageType

	private BooleanExpression regDateAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now();

		if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
			return null;
		} else if (StringUtils.equals("1d", searchDateType)) {
			dateTime = dateTime.minusDays(1);
		} else if (StringUtils.equals("1w", searchDateType)) {
			dateTime = dateTime.minusWeeks(1);
		} else if (StringUtils.equals("1m", searchDateType)) {
			dateTime = dateTime.minusMonths(1);
		} else if (StringUtils.equals("6m", searchDateType)) {
			dateTime = dateTime.minusMonths(6);
		} // end of else if regDateAfter

		return item.regTime.after(dateTime);
	}// end of regDateAfter

	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if (StringUtils.equals("itemNm", searchBy)) {
			return item.itemTitle.like("%" + searchQuery + "%");
		} else if (StringUtils.equals("createBy", searchBy)) {
			return item.createdBy.like("%" + searchQuery + "%");
		} // end of else if
		return null;
	}// end of searchByLike

	@Override
	public Page<Item> getStudentItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		List<Item> content = queryFactory.selectFrom(item).where(
				// 상품 판매 상태
				searchLanguageTypeEq(itemSearchDto.getLanguageType()),
				// 기간
				regDateAfter(itemSearchDto.getSearchDateType()),
				// 상품명 또는 등록자
				searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery())).orderBy(item.id.desc())
				.offset(pageable.getOffset()) // 시작 인덱스
				.limit(pageable.getPageSize()) // 한 번에 가져올 최대 개수
				.fetch();

		return new PageImpl<>(content, pageable, content.size());
	}// end of get StudentItem

	private BooleanExpression itemTitleLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? null : item.itemTitle.like("%" + searchQuery + "%");
	}// end of itemNmLike

	@Override
	public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		List<MainItemDto> results = queryFactory.select(
				// @QueryProjection을 사용하면 DTO로 바로 조회 가능
				// 엔티티 조회후 DTO로 변환하는 과정을 줄일 수 있음
				new QMainItemDto(item.id, item.itemTitle, item.itemField, item.itemContents, itemImg.imgUrl))
				.from(itemImg).join(itemImg.item, item) // itemImg와 item을 내부 조인 수행함
				.where(itemImg.repImgYn.eq("Y")) // 상품 이미지의 경우엔 대표 상품만 불러옴
				.where(itemTitleLike(itemSearchDto.getSearchQuery())).orderBy(item.id.desc())
				.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
		return new PageImpl<>(results, pageable, results.size());
	}// end of getMain

}// end of class
