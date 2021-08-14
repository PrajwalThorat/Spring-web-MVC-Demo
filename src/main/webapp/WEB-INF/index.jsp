<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<style>
		.main-form{
			margin-top:35px;
			width:450px;
			height:300px;
			margin-left:auto;
			margin-right:auto;
		}
		.table-form{
			width:1050px;
			margin-left:auto;
			margin-right:auto;
			
		}
		.table-form{
			text-align:center;
		}
		.table-form table a{
			text-decoration:none;
		}
		.table-form table td{
			border-top:none;
		}
		h2{
			text-align:center;
			padding-top:14px;
			color: tomato;
		}
	</style>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" 
	 rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" 
	 crossorigin="anonymous">
</head>
<body>
	<h2 class="display-6">USER DETAILS</h2>
	<hr/>
	<div class="main-form">
		<form method="POST" action="<c:url value="/addUser/"/>">
		  <div class="form-group mb-3">
		    <label for="name">Enter Your Name</label>
		    <input type="text" class="form-control" id="nameInput" name="name" value="${userItem.name }">
		  </div>
		  <div class="form-group mb-3">
		    <label for="email">Enter Your Email</label>
		    <input type="text" class="form-control" id="emailInput"name="email"value="${userItem.email }">
		  </div>
		  <div class="form-group mb-3" >
		    <label for="city">Enter Your City</label>
		    <input type="text" class="form-control" id="cityInput" name="city" value="${userItem.city }">
		  </div>
		  <c:if test="${! empty userItem.email}">
		  	<button type="submit" class="btn btn-info">Update User</button>
		  </c:if>
		  <c:if test="${empty userItem.email}">
		  	<button type="submit" class="btn btn-success">Add User</button>
		  </c:if>
		  
		</form>
	</div>

	<% int count=0;%>
	<div class="table-form">
		<table class="table table-striped">
	  <thead>
	    <tr class="table-warning">
	      <th scope="col">S.No</th>
	      <th scope="col">Name</th>
	      <th scope="col">Email</th>
	      <th scope="col">City</th>
	      <th scope="col">Action</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:if test="${!empty userList }">
	  	<c:forEach var="user" items="${userList}">
		  
		    <tr class="table-primary">
		      <td scope="row"><%=++count%></td>
		      <th>${user.name}</th>
		      <td>${user.email}</td>
		      <td>${user.city}</td>
		      <td>
		      		<a href="<c:url value="/updUser/${user.email}"/>">
									<button type="button" class="btn btn-primary">Update</button>
								</a>
		      		<a href="<c:url value="/delUser/${user.email}"/>">
									<button type="button" class="btn btn-danger">Delete</button>
								</a>
		      </td>
		    </tr>
		
	  	</c:forEach>
	  	 
	  </c:if>
	   </tbody>
	</table>
	</div>
</body>
</html>
