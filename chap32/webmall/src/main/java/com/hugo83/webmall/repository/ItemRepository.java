package com.hugo83.webmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugo83.webmall.entity.Item;
import com.hugo83.webmall.entity.ItemSellStatus;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 상품 이름으로 검색
    List<Item> findByItemNameContaining(String itemName);

    // 판매 상태로 검색
    List<Item> findByItemSellStatus(ItemSellStatus itemSellStatus);

    // 가격 범위로 검색
    List<Item> findByPriceBetween(int minPrice, int maxPrice);

    // 재고 수량이 특정 값 이상인 상품 검색
    List<Item> findByStockQuantityGreaterThanEqual(int stockQuantity);

    // 가격이 특정 값 이하인 상품 검색
    List<Item> findByPriceLessThanEqual(int price);

    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);
}
