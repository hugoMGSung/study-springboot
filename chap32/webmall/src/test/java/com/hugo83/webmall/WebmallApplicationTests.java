package com.hugo83.webmall;

import com.hugo83.webmall.repository.ItemRepository;
import com.hugo83.webmall.entity.Item;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.webmall.entity.ItemSellStatus;

@SpringBootTest
class WebmallApplicationTests {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	void insertItemJpa() {
		Item item = new Item();
		item.setItemName("테스트 상품");
		item.setPrice(10000);
		item.setItemDetail("테스트 상품 상세설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockQuantity(100);
		item.setCreatedAt(LocalDateTime.now());
		
		Item saveItem = this.itemRepository.save(item);
		System.out.println(saveItem.toString());
	}

	@Test
	void insertDummyItemsJpa() {
		for (int i = 0; i < 100; i++) {
			Item item = new Item();
			item.setItemName("테스트 상품" + (i+1));
			item.setPrice(10000);
			item.setItemDetail("테스트 상품 상세설명" + (i+1));
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockQuantity(100);
			item.setCreatedAt(LocalDateTime.now());
			
			this.itemRepository.save(item);
		}
	}

	@Test
	void selectListItemJpa() {
		List<Item> itemList = this.itemRepository
	}

	@Test
	void contextLoads() {
	}

}
