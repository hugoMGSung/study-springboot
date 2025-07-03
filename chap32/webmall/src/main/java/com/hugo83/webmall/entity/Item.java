package com.hugo83.webmall.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item") // 테이블 이름 지정
@Getter
@Setter
public class Item {

    @Id // Primary Key 지정
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;   // 상품 ID

    @Column(nullable=false, length = 150)
    private String itemName; // 상품 이름

    @Column(nullable=false)
    private int price; // 상품 가격

    @Column(nullable=false)
    private int stockQuantity; // 재고 수량

    @Lob // 대용량 데이터 저장을 위한 어노테이션
    @Column(nullable=false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING) // Enum 타입을 문자열로 저장
    private ItemSellStatus itemSellStatus; // 판매 상태 (판매중, 품절 등)

    @CreatedDate // 생성 일시 자동 저장
    @Column(updatable = false) // 수정 시 변경되지 않도록 설정
    private LocalDateTime createdAt; // 생성 일시

    @LastModifiedDate // 수정 일시 자동 저장
    private LocalDateTime updatedAt; // 수정 일시
}
