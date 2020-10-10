<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<a href="addBug.jsp">ADD BUG</a>
<a href="<%=request.getContextPath()%>/tester/logout">LOGOUT</a>

<table border="">

		<tr>
			<th>Bug Id</th>
			<th>Project Id</th>
			<th>Assigned To</th>
			<th>Title</th>
			<th>Description</th>
			<th>Open Date</th>
			<th>Marked For Closing</th>
			<th>Closed On</th>
			<th>Closed By</th>
			<th>Bug Status</th>
			<th>Severity Level</th>
			<th>Created By</th>
			
		</tr>


	<c:forEach var="bug" items="${listOfBugs}">
	<tr>
		<td><c:out value="${bug.bugId}" /></td>
		<td><c:out value="${bug.projectId}" /></td>
		
		<c:if test="${bug.assignedTo==0}">
			<td><c:out value="NA"/></td>
		</c:if>
		
		<c:if test="${bug.assignedTo!=0}">
			<td><c:out value="${bug.assignedTo}"/></td>
		</c:if>
		
		

		<td><c:out value="${bug.bugTitle}" /></td>
		<td><c:out value="${bug.bugDescription}" /></td>
		<td><c:out value="${bug.openDate}" /></td>
		<td><c:out value="${bug.markedForClosing}" /></td>
		
		
		
		 <c:choose>
				
			  <c:when test="${bug.closedOn=='1990-01-01'}">
					<td><c:out value="NA"/></td>  
			  </c:when>
			  
			  <c:otherwise>
			    	<td><c:out value="${bug.closedOn}"/></td>
			  </c:otherwise>
			  
		</c:choose>
		
		
		
	 	 <c:choose>
			  <c:when test="${bug.closedBy==0}">
					<td><c:out value="NA"/></td>  
			  </c:when>
			  
			  <c:otherwise>
			    	<td><c:out value="${bug.closedBy}"/></td>
			  </c:otherwise>
		</c:choose>
		
		<td><c:out value="${bug.bugStatus}" /></td>
		<td><c:out value="${bug.severityLevel}" /></td>
		<td><c:out value="${bug.createdBy}" /></td>
	</tr>
	
	
</c:forEach>

</table>
</body>
</html>