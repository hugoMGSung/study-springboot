package com.hugo83.webmall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebmallApplicationTests {

	// @Autowired
	// private ItemRepository itemRepository;

	@Test
	void contextLoads() {
	}

	// @Test
	// void insertDummyJpa() {
	// 	for (int i = 1; i <= 100; i++) {
	// 		Item item = new Item();
	// 		item.setItemName("상품" + i);
	// 		item.setPrice(1000 * i);
	// 		item.setStockQuantity(100 - i);
	// 		item.setItemDetail("상품 " + i + "의 상세 설명입니다.");
	// 		//  item.setItemSellStatus(i % 2 == 0 ? Item.ItemSellStatus.SELLING : Item.ItemSellStatus.SOLD_OUT);
	// 		item.setCreatedAt(java.time.LocalDateTime.now());
	// 		this.itemRepository.save(item);
			
	// 	}
	// }

}
