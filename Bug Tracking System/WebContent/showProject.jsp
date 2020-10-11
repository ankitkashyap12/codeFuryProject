<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Project Page</title>
</head>
<body>
<h3>Project Details:</h3>
<% List pList = (List) session.getAttribute("ProjectList");
 request.setAttribute("pList", pList);  %>

<table border=2 align=center width=800>
 <tr height=50>
   <th>Project ID</th>
   <th>Project Name</th>
   <th>Project Description</th>
   <th>Project StartDate</th>
   <th>Project Status</th>
 </tr>
 <c:forEach items="${pList}" var="project">
   
  <tr height=50>
   <td><c:out value="${project.projectId}"/></td>
   <td><c:out value="${project.projectName}"/></td>
   <td><c:out value="${project.projectDescription}"/></td>
   <td><c:out value="${project.startDate}"/></td>
   <td><c:out value="${project.projectStatus}"/></td>
    <td align=center style="border-right:hidden; border-top: hidden; border-bottom: hidden;"><form action="showBugs">
          <input type=hidden value="${project.projectId}" name="projectId"/>
          <input type="submit" class="btn btn-primary" value="Show Bugs">
          </form></td>
 </tr>
 </c:forEach>
</table>

</body>
</html>