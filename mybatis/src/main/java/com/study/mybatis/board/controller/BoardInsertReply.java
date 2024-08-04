package com.study.mybatis.board.controller;

import java.io.IOException;

import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardInsertReply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Reply req_reply = new Reply();

		System.out.println("BoardInsertReply_req_userId : " + request.getParameter("userId"));
		System.out.println("BoardInsertReply_req_userId : " + request.getParameter("replyText"));
		System.out.println("BoardInsertReply_req_userId : " + Integer.parseInt(request.getParameter("bno")));

		req_reply.setReplyWriter(request.getParameter("userId"));
		req_reply.setReplyContent(request.getParameter("replyText"));
		req_reply.setRefBno(Integer.parseInt(request.getParameter("bno")));

		/*
		 * System.out.println("BoardInsertReply_reply_getReplyWriter : " +
		 * req_reply.getReplyWriter());
		 * System.out.println("BoardInsertReply_reply_getReplyContent : " +
		 * req_reply.getReplyContent());
		 * System.out.println("BoardInsertReply_reply_getRefBno : " +
		 * req_reply.getRefBno());
		 */

		int result = new BoardServiceImpl().insertReply(req_reply);
		System.out.println("BoardInsertReply_result : " + result);
		System.out.println("BoardInsertReply_req : " + request);

		if (result > 0) {
//			request.setAttribute("replyflag", result);
			 request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
//			response.sendRedirect("WEB-INF/views/board/boardDetailView.jsp&replyflag=true");
		} else {
			request.setAttribute("errorMsg", "댓글작성 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}

	}

}
