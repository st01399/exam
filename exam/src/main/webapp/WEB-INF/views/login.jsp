<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
	function login() {
		if($("#id").val()!='' && $("#pw").val()!='') {
			$.ajax({
				method:"post",
				data:{"id":$("#id").val(),"pw":$("#pw").val()},
				url:"./loginSuccess.do",
				success:function(data){
					if(data.isc) {
						location.href="./boardList.do";
// 						document.forms[0].submit();
					} else {
						alert('해당 아이디가 없거나 비밀번호가 맞지 않습니다.');
					}
				}, error : function() {
					location.href="./error.do";
				}
				
			});
		}
	}
</script>
<body>
	<table>
		<tbody>
			<tr>
				<th>
					<label for="id">아이디 :</label> 
				</th>
				<td>
					<input type="text" id="id" name="id">
				</td>
			</tr>
			<tr>
				<th>
					<label for="pw">비밀번호 :</label> 
				</th>
				<td>
					<input type="password" id="pw" name="pw">
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<input type="button" value="로그인" onclick="login()">
					<input type="button" value="회원가입" onclick="location.href='./registerMove.do';">
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>