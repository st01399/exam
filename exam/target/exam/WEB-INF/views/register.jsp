<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
	var idTrue = false;
	
	onload= function() {
		$("#id").keyup(function(){
			var id= $("#id").val();
			if(id.length >0 ) {
				$.ajax({
					method : "post",
					url : "./idCheck.do",
					data : {"id":id},
					success : function(data) {
						if(data.isc) {
							$("#checkId").html("사용가능한 아이디 입니다.");
							$("#checkId").css("color","green");
							idTrue = true;
						} else {
							$("#checkId").html("사용불가능한 아이디 입니다.");
							$("#checkId").css("color","red");
							idTrue = false;
						}
					},
					error : function() {
						location.href="./error.do";
					}
				});
			}
		});
		$("#pw").keyup(function(){
			var pw = $("#pw");
			var pw2 = $("#pw2");
			if(pw.val() != pw2.val()) {
				$("#checkPw").html("비밀번호가 일치하지 않습니다.");
				$("#checkPw").css("color","red");
			} else {
				$("#checkPw").html("비밀번호가 일치합니다.");
				$("#checkPw").css("color","green");
			}
		});
		$("#pw2").keyup(function(){
			var pw = $("#pw");
			var pw2 = $("#pw2");
			if(pw.val() != pw2.val()) {
				$("#checkPw").html("비밀번호가 일치하지 않습니다.");
				$("#checkPw").css("color","red");
			} else {
				$("#checkPw").html("비밀번호가 일치합니다.");
				$("#checkPw").css("color","green");
			}
		});
	}
	
	
	function regist() {
		var id = $("#id");
		var pw = $("#pw");
		var pw2 = $("#pw2");
		var name = $("#name");
		if(id.val() == '') {
			alert('아이디를 입력하세요.');
		} else if(pw.val() == '') {
			alert('패스워드를 입력하세요.');
		} else if(pw.val() != pw2.val()) {
			alert('비밀번호가 일치하지 않습니다.');
		} else if(name.val() == '') {
			alert('이름을 입력하세요.');
		} else if(!idTrue) {
			alert('다른 아이디를 사용하세요.');
		} else {
			document.forms[0].submit();
		}
	}
	
</script>
<body>
	<form action="./register.do" method="POST">
		<table border="1">
			<tbody>
			<tr>
				<th>
					<label for="id">아이디 :</label> 
				</th>
				<td>
					<input type="text" id="id" name="id">
				</td>
				<td>
					<div id="checkId"></div>
				</td>
			</tr>
			<tr>
				<th>
					<label for="name">이름 :</label> 
				</th>
				<td>
					<input type="text" id="name" name="name">
				</td>
			</tr>
			<tr>
				<th>
					<label for="pw">비밀번호 :</label> 
				</th>
				<td>
					<input type="password" id="pw" name="pw">
				</td>
				<td>
					<div id="checkPw"></div>
				</td>
			</tr>
			<tr>
				<th>
					<label for="pw2">비밀번호 확인 :</label> 
				</th>
				<td>
					<input type="password" id="pw2">
				</td>
			</tr>	
			</tbody>
			<tfoot>
				<tr>
					<td>
						<input type="button" value="회원가입" onclick="regist()">
						<input type="button" value="돌아가기" onclick="history.back(-1)">
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>