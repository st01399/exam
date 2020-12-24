<%@page import="com.min.usebean.TitleReply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
</head>
<script type="text/javascript">
	function allChk(chk) {
		var chks= document.getElementsByName("chks");
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=chk;
		}
	}
	
	function checkLength() {
		var chks= document.getElementsByName("chks");
		var ckTotal = 0;
		for (var i = 0; i < chks.length; i++) {
			if(chks[i].checked) {
				ckTotal++;
			} else {
				return 0;
			}
		}
		return ckTotal;
	}
	
	window.onload = function() {
		var chks = document.getElementsByName("chks");
			for (var i = 0; i < chks.length; i++) {
				chks[i].onclick= function() {
					var chks = document.getElementsByName("chks");
					if(chks.length == checkLength()) {
						document.getElementById("allChks").checked= true;
					} else {
						document.getElementById("allChks").checked= false;
					}
				}	
			}	
	}
	
	function ckTrue() {
		var chks= document.getElementsByName("chks");
		for (var i = 0; i < chks.length; i++) {
			if(chks[i].checked) {
				return true;
			}
		}
		return false;
	}
	
	function multiDel() {
		if(!ckTrue()) {
			alert('하나 이상 체크하세요.');
			return false;
		} 
		return true;
	}
	
</script>

<body>
	<form action="./multiDelete.do" method="post" onsubmit="return multiDel()">
	<table border="1">
		<thead>
		<tr>
			<th><input type="checkbox" id="allChks" onclick="allChk(this.checked)"></th>
			<th>연변</th>
			<th>아이디</th>
			<th>제목</th>
			<th>등록일</th>
		</tr>
		</thead>
		<jsp:useBean id="title" class="com.min.usebean.TitleReply"/>
		<tbody>
		<c:forEach varStatus="vs" var="dto" items="${lists}">
			<tr>
				<td><input type="checkbox" name="chks" value="${dto.seq}"></td>
				<td>${fn:length(lists)-vs.index}</td>
				<td>${dto.id}</td>
				<td><a href="./detailBoard.do?seq=${dto.seq}">${title.title(dto.title,dto.depth)}</a></td>
				<td>${dto.regdate}</td>
			</tr>
		</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input type="button" value="글작성" onclick="location.href='./writeMove.do'"> 
				<input type="submit" value="다중삭제">
				<input type="button" value="로그아웃" onclick="logout()">
				</td>
			</tr>
		</tfoot>		
	</table>
	</form>
</body>
<script type="text/javascript">
	function logout() {
		location.href="./logout.do";
	}
</script>
</html>