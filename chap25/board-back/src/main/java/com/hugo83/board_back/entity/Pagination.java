package com.hugo83.board_back.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {
	// 페이지당 보여지는 게시글의 최대 개수
	private int pageSize;

	private int page; // 현재 페이지

	private int totalPageCnt; // 총 페이지 수

	private long totalListCnt; // 총 게시글 수

	private int startPage; // 시작 페이지
	private int endPage; // 마지막 페이지

	private int startIndex; // 

	//현재 블럭
	int block;
	//총 구간 수
	int totalBlockCnt;
	// 이전 구간 마지막 페이지
	int prevBlock;
	// 다음 구간 시작 페이지
	int nextBlock;

	public Pagination(Long totalListCnt, Integer page, Integer pageSize, Integer blockSize) {
		this.pageSize = pageSize;
		this.page = page;
		this.totalListCnt = totalListCnt;
		this.totalPageCnt = (int) Math.ceil(totalListCnt * 1.0 / this.pageSize);
		//총 블럭 수
        this.totalBlockCnt = (int) Math.ceil(this.totalPageCnt * 1.0 / blockSize);
		//현재 블럭
        this.block = (int) Math.ceil((this.page * 1.0) / blockSize);
		//블럭 시작 페이지
        this.startPage = ((this.block - 1) * blockSize + 1);
		//블럭 마지막 페이지
        this.endPage = this.startPage + blockSize - 1;
		//블럭 마지막 페이지 validation
		if (this.endPage > this.totalPageCnt) this.endPage = this.totalPageCnt;
		// 이전 블럭 (클릭 시, 이전 블럭 마지막 페이지)
		this.prevBlock = (this.block * blockSize) - blockSize; 
		// 이전 블럭 validation
		if (this.prevBlock < 1) this.prevBlock = 1;
		//다음 블럭 (클릭 시, 다음 블럭 첫번째 페이지)
		this.nextBlock = (this.block * blockSize + 1);
		// 다음 블럭 validation
		if (this.nextBlock > this.totalPageCnt) this.nextBlock = this.totalPageCnt;
		//if(this.page < 1) this.page = 1
		this.startIndex = (this.page - 1) * this.pageSize;
	}
}
