<%--
  Created by IntelliJ IDEA.
  User: jyzxc
  Date: 2024/12/1
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>留言板</title>
    <style>
        body {
            background: linear-gradient(0deg, #d9d9d9 0%, #f2f2f2 100%);
            display: flex;
            justify-content: center;
            align-items: start;
            padding: 20px;
        }
    </style>
</head>
<body>
<div class="flex justify-center flex-col">
    <div class="bg-white shadow hover:shadow-lg rounded-lg w-1/2 p-4 transition-all mt-10 w-[1024px] flex justify-between">
        <div class="font-bold">
            留言板
        </div>
        <div>
        <c:choose>
            <c:when test="${user != null}">
                <span
                        class="text-md text-gray-500">
                你好, <b>${user.username}</b></span>
                <a
                        href="${pageContext.request.contextPath}/page/logout"
                        class="text-md text-blue-500 hover:text-blue-700 transition-all hover:underline ml-5"
                >登出</a>
            </c:when>
            <c:otherwise>
                <a
                        href="${pageContext.request.contextPath}/page/login"
                        class="text-md text-blue-500 hover:text-blue-700 transition-all hover:underline ml-5"
                >请登录</a>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
    <c:if test="${user != null}">
        <div class="bg-white shadow hover:shadow-lg rounded-lg w-1/2 p-4 transition-all mt-5 w-[1024px]">
            <h2 class="text-2xl font-medium mb-5">
                提交留言
            </h2>
            <c:if test="${not empty error}">
                <div class="bg-red-100 text-red-500 p-2 rounded-lg">
                        ${error}
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/page/messages" method="post">
                <label>
                    <input type="text" name="title" placeholder="消息标题" required
                           class="w-full border border-gray-300 p-2 rounded-lg mt-2 hover:shadow transition-all">
                </label>
                <label>
                    <textarea name="content" placeholder="消息内容" required
                              class="w-full border border-gray-300 p-2 rounded-lg mt-2 hover:shadow transition-all"></textarea>
                </label>
                <button type="submit"
                        class="bg-gray-500 text-white px-4 py-2 rounded font-medium hover:bg-gray-600 transition-all mt-5">
                    提交消息
                </button>
            </form>
        </div>
    </c:if>
    <div class="bg-white shadow hover:shadow-lg rounded-lg w-1/2 p-4 transition-all mt-5 w-[1024px]">
        <h2 class="text-2xl font-medium mb-5">
            消息列表
        </h2>
        <ul>
            <jsp:useBean id="messages" scope="request" type="java.util.List"/>
            <c:forEach items="${messages}" var="message">
                <li class="p-2 hover:bg-gray-200 transition-all border-b border-gray-300">
                    <a href="${pageContext.request.contextPath}/page/message/${message.id}"
                       class="flex justify-between items-center">
                        <h3 class="text-xl font-medium">
                            <span>#${message.id} ${message.title}</span>
                        </h3>
                        <span class="ml-auto text-gray-500 text-sm">由 ${message.createdBy.username}于 ${message.createdAt}提交</span>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
