<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 입력</title>
</head>
<body>
	<form action="" method="post" onsubmit="return validate()">
		<input type="hidden" name="seq" value="${seq}">
		<table border="1">
			<tbody>
				<tr>
					<th>아이디 : </th><td>
					<label>${infomation.id}</label>
					<input type="hidden" id="id" name="id" value="${infomation.id}"></td>
				</tr>
				<tr>
					<th>제목 : </th><td><input type="text" id="title" name="title"></td>
				</tr>
				<tr>
					<th>내용 : </th><td><textarea rows="10" cols="60" id="content" name="content"></textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<input type="submit" value="입력">
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
			var form = document.forms[0];
			if(form.seq.value == '') {
				form.action="./write.do";
			} else {
				form.action="./reply.do";
			}
			return true;
		}
	</script>
</body>
</html>