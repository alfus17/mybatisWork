<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<style>
.outer table {
	border: 2px solid;
	border-collapse: collapse;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="outer" align="center">
		<h1 align="center">게시판 상세조회</h1>

		<table border="1">
			<tr>
				<td width="100">글번호</td>
				<td width="500">${ b.boardNo }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${ b.boardTitle }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${ b.boardWriter }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${ b.count }</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${ b.createDate }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td height="100">${ b.boardContent }</td>
			</tr>
		</table>
		<br>
		<table border="1">
			<tr>
				<!-- Todo 여기 파라미터값 제대로 보내줘야함   -->
			<%-- 	<form action="Reply.bo?userId=${loginUser.userId}&bno=${ b.boardNo }" method="post"> --%>

					<th width="100">댓글작성</th>
					<th width="400"><textarea  id="replyText" cols="53" rows="3" name="replyText"></textarea></th>
					<th width="100">
					
					<c:choose>
					<c:when test="${ empty loginUser }">
						<button  disabled>등록</button></th>
					</c:when>
					<c:otherwise>
						<button id="submitBtn">등록</button>
					</c:otherwise>
					</c:choose>
					</th>

				<!-- </form> -->
			</tr>
			<tr>
				<th colspan="3" style="text-align: center">댓글(${rlist.size()})</th>
			</tr>

			<c:forEach var="r" items="${rlist}">
				<tr>
					<td>${r.replyWriter}</td>
					<td>${r.replyContent}</td>
					<td>${r.createDate.substring(0,10)}</td>
				</tr>
			</c:forEach>

		</table>
		<script>
		<%-- 	<form action="Reply.bo?userId=${loginUser.userId}&bno=${ b.boardNo }" method="post"> --%>
		$(() => {
			$("#submitBtn").click(function() {
				$.ajax({
					url : "Reply.bo",
					data : {replyText : $("#replyText").val(),
							userId :"${loginUser.userId}",
							bno : "${b.boardNo }"	
					},
					type : "post",
					success : function(data) {
						console.log("ajax통신 성공");
						console.log(window.location.pathname + "?replyflag=true");
						//location.href = window.location.pathname + "?replyflag=true";
					},
					error : function() {
						console.log("ajax통신 실패");
					}
				})
				
			})
			
		})
	</script>
	</div>
</body>
</html>