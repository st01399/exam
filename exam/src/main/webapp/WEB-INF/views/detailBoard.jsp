<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세글 보기</title>
</head>
<body>
	<table border="1">
		<tbody>
			<tr>
				<th>아이디 : </th><td>${dto.id}</td>
				<th>등록일 : </th><td>${dto.regdate}</td>
			</tr>
			<tr>
				<th>제목 : </th><td colspan="3">${dto.title}</td>
			</tr>
			<tr>
				<th>내용 : </th><td colspan="3">${dto.content}</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<input type="button" value="답글입력" onclick="location.href='./replyMove.do?seq=${dto.seq}'">
					<input type="button" value="수정" onclick="location.href='./modifyMove.do?seq=${dto.seq}'">
					<input type="button" value="삭제" onclick="location.href='./delete.do?seq=${dto.seq}'">
					<input type="button" value="돌아가기" onclick="location.href='./boardList.do'">
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>