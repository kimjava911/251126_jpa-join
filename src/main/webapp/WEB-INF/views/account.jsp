<%@ page import="kr.java.join.model.entity.UserAccount" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.java.join.model.entity.UserProfile" %>
<%@ page import="kr.java.join.model.entity.Post" %>
<%@ page import="kr.java.join.model.entity.Recommend" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>계정 페이지</title>
</head>
<body>
    <%-- recommend --%>
    <%= (List<Recommend>) request.getAttribute("recommends") %>

    <%-- insertForm--%>
    <form method="post" action="recommend">
        <input name="accountId" placeholder="유저ID"><br>
        <input name="postId" placeholder="게시물ID"><br>
        <button>추천</button>
    </form>

    <%-- post --%>
    <%
        List<Post> posts = (List<Post>) request.getAttribute("posts");
        for (Post p : posts) {
            UserAccount ua = p.getUserAccount();
            UserProfile up = ua.getProfile();
    %>
    <div>
        <hr>
        <p> 작성자 : <%= ua.getUsername() %> </p>
        <p> 작성자별명 : <%= up.getNickname() %> </p>
        <p> 내용 : <%= p.getContent() %></p>
    </div>
    <% } %>

    <%-- insertForm--%>
    <form method="post" action="post">
        <input name="accountId" placeholder="유저ID"><br>
        <input name="content" placeholder="내용"><br>
        <button>작성</button>
    </form>

    <%-- findAll --%>
    <%
        List<UserAccount> accounts = (List<UserAccount>) request.getAttribute("accounts");
        for (UserAccount ua : accounts) {
            UserProfile up = ua.getProfile();
            UserAccount ua2 = up.getUserAccount(); // -> 양방향 매핑 -> ua
            UserProfile up2 = ua2.getProfile();
            // ...
    %>
        <%= ua %>
        <%= up %>
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
