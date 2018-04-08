<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value="/resources/css/vendor/bootstrap.min.css" />">

<link href="<c:url value="/resources/css/app/style.css"/>"
	rel="stylesheet" type="text/css">

<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>

<body class="bg-dark">

	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<!-- Logo -->
					<div class="logo">
						<h1>
							<a href="${pageContext.request.contextPath }">Online Voting
								System</a>
						</h1>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form:form
					action="${pageContext.request.contextPath }/authenticateUser"
					method="POST">
					<div class="login-wrapper">
						<div class="box">
							<div class="content-wrap">
								<h6>Sign In</h6>

								<c:if test="${param.error!=null }">
									<span class="error">Sorry! You entered invalid
										username/password</span>
								</c:if>
								<c:if test="${param.logout!=null }">
									<span class="logout">Logged out successfully</span>
								</c:if>
								<c:if test="${param.registration_success!=null }">
									<span class="logout">Registration Complete ! Please login!</span>
								</c:if>

								<div class="form-group">
									<label for="username">User Name</label> <input type="text"
										class="form-control" name="username"
										placeholder="Enter Username">
								</div>
								<div class="form-group">
									<label for="password">Password</label> <input
										class="form-control" id="password" type="password"
										placeholder="Password" name="password">
								</div>
								<div class="action">
									<input type="submit" class="btn btn-primary signup" value="Login" />
								</div>
							</div>
						</div>

						<div class="already">
							<p>Don't have an account yet?</p>
							<a href="${pageContext.request.contextPath }/register">Sign Up</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>


</html>