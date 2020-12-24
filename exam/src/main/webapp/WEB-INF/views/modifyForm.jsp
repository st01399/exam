<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
	<form action="./modify.do" method="post" onsubmit="return validate()">
	<input type="hidden" value="${dto.seq}" name="seq">
	<table border="1">
		<tbody>
			<tr>
				<th>아이디 : </th><td>
				<input type="text" value="${infomation.id}" readonly="readonly" id="id" name="id"></td>
			</tr>
			<tr>
				<th>제목 : </th><td><input type="text" value="${dto.title}" id="title" name="content" readonly="readonly"></td>
			</tr>
			<tr>
				<th>내용 : </th><td><textarea rows="10" cols="60" id="content" name="content">${dto.content}</textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="돌아가기" onclick="history.back(-1)">
				</td>
			</tr>
		</tfoot>
	</table>
	</form>
	<script type="text/javascript">
	function validate() {
		var id= document.getElementById('id');
		var title= document.getElementById('title');
		var content= document.getElementById('content');
		if(id.value == '') {
			alert('아이디를 입력하세요.');
			return false;
		} else if(title.value == '') {
			alert('제목을 입력하세요.');
			return false;
		} else if(content.value == ''){
			alert('내용을 입력하세요.');
			return false;
		}
		return true;
	}
	</script>
</body>
</html>