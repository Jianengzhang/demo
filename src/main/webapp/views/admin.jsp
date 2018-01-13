<%--
  Created by IntelliJ IDEA.
  User: Aron
  Date: 18-1-8
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>修改站点</title>
</head>
<body>
<table id="mytable">
    <thead>
    <tr>
        <th>车站名</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stations}" var="station">
        <tr>
            <td id="station">${station}</td>
            <td><a id="deleteStation" onclick="deleteStation(this)"><i class="fa fa-check text-navy"></i> 删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <div>
        <label>请输入需要添加的站名</label>
        <input type="text" id="addStationName">
        <a id="addStation1" onclick="addStation()">添加</a>
    </div>
</div>
<section class="container">
    <div class="partDivide">
        <div>
            <input type="text" id="addTrain" placeholder="请输入车次">
        </div>
        <label>请选择站点</label>
        <div>
            <input type="text" id="addStation" placeholder="请输入站名">
        </div>
        <div>
            <input type="time" id="leave" placeholder="请输入离开时间">
        </div>
        <div>
            <input type="time" id="arrive" placeholder="请输入到达时间">
        </div>
        <div>
            <input type="text" id="distance" placeholder="请输入距离起始站的距离">
        </div>
        <div>
            <button onclick="addStationToTrain()">添加</button>
        </div>

    </div>
    <div>
        <label>删除车次信息</label>
    </div>
</body>
<script type="text/javascript" src="/js/admin.js"></script>
<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
</html>
