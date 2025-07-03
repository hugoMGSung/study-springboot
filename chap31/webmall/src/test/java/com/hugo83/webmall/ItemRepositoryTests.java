package com.hugo83.webmall;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.webmall.entity.Item;
import com.hugo83.webmall.entity.ItemSellStatus;
import com.hugo83.webmall.repository.ItemRepository;

@SpringBootTest
public class ItemRepositoryTests {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    void insertItemJpa() {
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockQuantity(100);
        item.setCreatedAt(LocalDateTime.now());

        this.itemRepository.save(item);;
    }
    
}
