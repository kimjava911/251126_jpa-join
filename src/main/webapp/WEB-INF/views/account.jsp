<%@ page import="kr.java.join.model.entity.UserAccount" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.java.join.model.entity.UserProfile" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>계정 페이지</title>
</head>
<body>
    <%-- findAll --%>
    <%
        List<UserAccount> accounts = (List<UserAccount>) request.getAttribute("accounts");
        for (UserAccount ua : accounts) {
            UserProfile up = ua.getProfile();
    %>
        <div>
            <p> 유저네임 : <%= ua.getUsername() %> </p>
            <p> 유저별명 : <%= up.getNickname() %> </p>
        </div>
    <% } %>

    <%-- insertForm--%>
    <form method="post">
        <input name="username" placeholder="유저이름"><br>
        <input name="nickname" placeholder="유저별명"><br>
        <button>추가</button>
    </form>
</body>
</html>
