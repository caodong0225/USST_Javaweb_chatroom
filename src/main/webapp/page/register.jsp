<%--
  Created by IntelliJ IDEA.
  User: lutiancheng
  Date: 2023/11/24
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Register</title>
    <style>
        body {
            background: linear-gradient(0deg, #d9d9d9 0%, #f2f2f2 100%);
            padding: 20px;
        }
    </style>
</head>
<body>
<div class="flex justify-center">
    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 flex flex-col my-2 mt-10 w-[350px] hover:shadow-xl transition-all">
        <h1 class="text-center font-bold text-4xl">注册</h1>
        <p class="text-center text-gray-500 text-xs">
            已有账号? <a href="${pageContext.request.contextPath}/page/login" class="text-indigo-500 hover:text-indigo-700 no-underline hover:underline cursor-pointer transition-all">点我登录</a>
        </p>
        <form
                action="${pageContext.request.contextPath}/page/register"
                method="post"
                class="flex flex-col pt-3">

            <div class="text-red-500 text-sm">
                ${error}
            </div>

            <label for="username" class="text-gray-500 text-md mt-2.5">* 用户名</label>
            <input
                    type="text"
                    id="username"
                    name="username"
                    placeholder="用户名"
                    class="appearance-none border rounded w-full py-2 px-3 text-gray-700 mt-1 leading-tight hover:shadow transition-all"
                    maxlength="20"
                    required
            />
            <label for="password" class="text-gray-500 text-md mt-2.5">* 密码</label>
            <input
                    type="password"
                    id="password"
                    name="password"
                    placeholder="密码"
                    class="appearance-none border rounded w-full py-2 px-3 text-gray-700 mt-1 leading-tight hover:shadow transition-all"
                    min="0"
                    max="200"
                    required
            />
            <input
                    type="submit"
                    class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded cursor-pointer mt-5 transition-all"
                    value="注册"
            />
        </form>
    </div>
</div>
</body>
</html>
