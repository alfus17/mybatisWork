package com.study.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.study.mybatis.board.service.BoardService;
import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));

		BoardService bService = new BoardServiceImpl();

		// request에 우리가 flag 설정해주기

		System.out.println("BoardDetailController_req_replyflag :" + request.getParameter("replyflag"));
		String replyInput = request.getParameter("replyflag");
		// 만약 플래그 없을 때만 증가 시키기
//		if (replyInput != null) {
		// 1. 조회수 증가
		int result = bService.increaseCount(boardNo);
//		}

//		int countReply = bService.countReply(boardNo);

		if (result > 0) {
			// 2. 상세 조회
			Board b = bService.selectBoard(boardNo);

			// 3. 해당글에 달린 댓글 리스트 조회
			ArrayList<Reply> rlist = bService.selectReplyList(boardNo);

			request.setAttribute("b", b);
			request.setAttribute("rlist", rlist);
			request.setAttribute("rlist", rlist);

			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "상세조회 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
