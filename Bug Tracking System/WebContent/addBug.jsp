<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Bug</title>
</head>
<body>

<h1>ADD BUG PAGE</h1>

<form action="<%=request.getContextPath()%>/tester/addBug" method="post">

<div>
<label>ProjectId:</label>

<select name="projectId">
    <c:forEach var="item" items="${projectList}">
        <option value="${item}">${item}</option>
    </c:forEach>
</select>
</div>


<div>
<label>Bug Title:</label>
<input type="text" id="bugTitle" name="bugTitle" required="required" placeholder="Bug Title">

</div>

<div>
<label>Bug Description:</label>
<input type="text" id="bugDescription" name="bugDescription" required="required" placeholder="Bug Description">
</div>

<div>

<label>Severtiy Level:</label>

<select id="severityLevel" name="severityLevel">
  <option value="trivial">Trivial</option>
  <option value="minor">Minor</option>
  <option value="major">Major</option>
  <option value="critical">Critical</option>
</select>
</div>



<div>
<input type="submit" value="SUBMIT">

</div>





</form>



<form>
</body>
</html>