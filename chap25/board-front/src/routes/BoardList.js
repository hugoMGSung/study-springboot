/* BoardList.js */
import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

import * as common from '../common/CommonFunc';

const BoardList = () => {
	const [boardList, setBoardList] = useState([]);
	const [pageList, setPageList] = useState([]);
	let [lastPage, setLastPage] = useState(0);

	const getBoardList = async (number) => {
		var pageString = (number == null) ? 'page=0' : 'page=' + number; 
		const resp = (await axios.get("//localhost:8080/api/board/list/freeboard?" + pageString)).data;
		setBoardList(resp.data);
		console.log(resp.data);

		const pngn = resp.pagination;
		// console.log(pngn);

		const { endPage, nextBlock, prevBlock, startPage, totalPageCnt } = pngn;

		setLastPage(totalPageCnt);
		console.log(startPage);
		console.log(endPage);
		console.log(nextBlock);
		console.log(prevBlock);
		console.log(totalPageCnt);

		const tmpPages = [];
		for (let i = startPage; i <= endPage; i++) {
			tmpPages.push(i);
		}
		setPageList(tmpPages);
	};

	function onClick(page) {
		console.log(page);
		getBoardList(page - 1);
	};


	useEffect(() => {
		getBoardList(); // 1) 게시글 목록 조회 함수 호출
	}, []);

	return (
	<div className="container">
		 <table className="table">
			<thead className="table-dark">
				<tr className="text-center">
					<th>번호</th>
					<th style={{ width:'50%' }}>제목</th>
					<th>글쓴이</th>
					<th>조회수</th>
					<th>작성일시</th>
				</tr>
			</thead>
			<tbody>
				{boardList.map((board) => (
					// map 함수로 데이터 출력
					<tr className="text-center" key={board.bno}>
						<td>{board.bno}</td>
						<td className="text-start">
							<Link to={`/api/board/${board.bno}`}>{board.title}</Link>
						</td>
						<td key={board.author.id}>{board.author.username}</td>
						<td>{board.hit}</td>
						<td>{common.formatDate(board.createDate)}</td>
					</tr>
				))}
				{/* 
					<td className="text-start">
						<a th:href="@{|/board/detail/${board.Bno}|}" th:text="${board.title}"></a>
						<span className="text-warning small ms-2"
							th:if="${#lists.size(board.replyList) > 0}"
							th:text="${#lists.size(board.replyList)}"></span>
					</td>
					<td><span th:if="${board.author != null}" th:text="${board.author.username}"></span></td>
					<td><span th:text="${board.hit}"></span></td>
					<td th:text="${#temporals.format(board.createDate, 'yyyy-MM-dd HH:mm')}"></td> */}
			</tbody>
		</table>
		
		<div className="d-flex justify-content-center">
			<nav aria-label="Page navigation">
				<ul className="pagination">
					<li className="page-item">
						<button className="page-link" aria-label="Previous" onClick={() => onClick(1)}>
							<span aria-hidden="true">&laquo;</span>
						</button>
					</li>
					{pageList.map((page, index) => (
						<li className="page-item" key={index}>
							<button className="page-link" onClick={() => onClick(page)}>{page}</button>
						</li>
					))}
					<li className="page-item">
						<button className="page-link" aria-label="Next" onClick={() => onClick(lastPage)}>
							<span aria-hidden="true">&raquo;</span>
						</button>
					</li>
				</ul>
			</nav>
		</div>
	</div>
  
  );
};

export default BoardList;
