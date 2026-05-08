<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon2.png" type="image/x-icon">
<link href="/css/common.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  

<style>
  body {
    background-color: #f5f6f8;
    font-family: "Pretendard", "Noto Sans KR", sans-serif;
    color: #222;
  }

  h2 {
    font-size: 28px;
    font-weight: 800;
    margin: 25px 0;
    color: #111;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    background: white;
    border: 1px solid #999;
  }

  td {
    padding: 18px 22px;
    border-bottom: 1px solid #999;
    font-size: 15px;
  }
  .menu {
    & td {
        a {
            background-color: #9eecff;
	        }
	    }
	}

  td:nth-child(1),
  td:nth-child(3) {
    width: 140px;
    background: #35d7ff;
    font-weight: 700;
    color: white;
    text-align: center;
    border: 1px solid white;
  }

  td:nth-child(2),
  td:nth-child(4) {
    background: white;
    color: #111827;
  }

  tr:nth-child(3) td:nth-child(2) {
    font-size: 20px;
    font-weight: 700;
    padding-top: 24px;
    padding-bottom: 24px;
  }

  tr:nth-child(4) td:nth-child(2) {
    height: 500px;
    line-height: 2;
    font-size: 16px;
    vertical-align: top;
    padding-top: 35px;
    padding-bottom: 80px;
    white-space: pre-wrap;
  }

  tr:last-child td {
    background: #9eecff;
    text-align: center;
    padding: 30px 20px;
    border-bottom: none;
  }

  .btn {
    min-width: 100px;
    height: 42px;
    margin: 0 6px;
    border-radius: 8px;
    font-weight: 600;
  }
</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>


<body> 
  <main>
    <!-- 메뉴 출력 -->
    <%@include file="/WEB-INF/include/menus.jsp" %>
    
    <h2>게시글 내용 보기</h2>
     <table>
      <tr>
        <td>글 번호</td>
        <td>${board.idx}</td>
        <td>조회수</td>
        <td>${board.hit}</td>      
      </tr>
      <tr>
        <td>작성자</td>
        <td>${board.writer}</td>
        <td>작성일</td>
        <td>${board.regdate}</td>      
      </tr>
      <tr>
        <td>제목</td>
        <td colspan="3">${ board.title }</td>
      </tr>
      <tr>
        <td>내용</td>
        <td colspan="3">${ board.content }</td>
      </tr>
     
      <tr>
        <td colspan="4">
          <a href="/Board/WriteForm?menu_id=${menu_id}"  class="btn btn-primary">새글쓰기</a>
          <a href="/Board/UpdateForm?idx=${board.idx}&menu_id=${menu_id}" class="btn btn-warning">수정</a>
          <a href="/Board/Delete?idx=${board.idx}&menu_id=${board.menu_id}" class="btn btn-danger">삭제</a>
          <a href="/Board/List?menu_id=${menu_id}" class="btn btn-info">목록</a>
          <a href="/" class="btn btn-success">Home</a>
        </td>
      </tr>
     </table>    
  
  </main>
  

  
</body>
</html>    















