<%--
  Created by IntelliJ IDEA.
  User: jyzxc
  Date: 2024/12/1
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="session" type="top.caodong0225.usst_noteboard.vo.UserVO"/>
<jsp:useBean id="message" scope="request" type="top.caodong0225.usst_noteboard.vo.MessageDetailedVO"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>消息 #${message.id}</title>
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
        <div>
            <a
                    href="${pageContext.request.contextPath}/page/message"
                    class="text-md text-blue-500 hover:text-blue-700 transition-all hover:underline"
            >&lt; 返回消息列表</a>
        </div>
        <div>
            <span
                    class="text-md text-gray-500">
            你好, <b>${user.username}</b></span>
            <a
                    href="${pageContext.request.contextPath}/page/logout"
                    class="text-md text-blue-500 hover:text-blue-700 transition-all hover:underline ml-5"
            >登出</a>
        </div>
    </div>
    <div class="bg-white shadow hover:shadow-lg rounded-lg w-1/2 p-4 transition-all mt-5 w-[1024px]">
        <h2 class="text-2xl font-medium mb-2.5">
            ${message.title}
        </h2>
        <p class="text-gray-500 text-sm mb-5">
            由 ${message.createdBy.username} 于 ${message.createdAt} 提交
        </p>
        <p class="text-gray-950 text-md mb-5">
            ${message.content}
        </p>
    </div>
</div>
</body>
</html>
