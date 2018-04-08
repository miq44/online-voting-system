<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Voting System Sign up Page</title>
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
<body class="login-bg">

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
			<form:form class="form-horizontal"
				action="${pageContext.request.contextPath }/registerUser"
				method="POST" modelAttribute="newUser">
				<div class="col-md-12">
					<div class="login-wrapper signup">
						<div class="box">
							<div class="content-wrap">
								<h6>Sign Up</h6>
								<div class="form-group">
									<label for="username" class="col-sm-3 control-label">User
										Name</label>
									<div class="col-sm-9">
										<form:input path="userName" placeholder="User Name"
											cssClass="form-control" autofocus="autofocus" />
										<span class="help-block"><form:errors path="userName"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="emailId" class="col-sm-3 control-label">Email
										ID</label>
									<div class="col-sm-9">
										<form:input path="emailId" type="email" placeholder="Email ID"
											cssClass="form-control" autofocus="autofocus" />
										<span class="help-block"><form:errors path="emailId"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="firstName" class="col-sm-3 control-label">First
										Name</label>
									<div class="col-sm-9">
										<form:input type="text" path="firstName"
											placeholder="First Name" cssClass="form-control"
											autofocus="autofocus" />
										<span class="help-block"><form:errors path="firstName"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="lastName" class="col-sm-3 control-label">Last
										Name</label>
									<div class="col-sm-9">
										<form:input type="text" path="lastName"
											placeholder="Last Name" cssClass="form-control"
											autofocus="autofocus" />
										<span class="help-block"><form:errors path="lastName"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="password" class="col-sm-3 control-label">Password</label>
									<div class="col-sm-9">
										<form:input type="password" path="password"
											placeholder="Password" cssClass="form-control"
											autofocus="autofocus" />
										<span class="help-block"><form:errors path="password"
												cssClass="error" /></span>
									</div>
								</div>
								<div class="form-group">
									<label for="retypePassword" class="col-sm-3 control-label">Retype
										Password</label>
									<div class="col-sm-9">
										<form:input type="password" path="confirmPassword"
											placeholder="Retype Password" cssClass="form-control"
											autofocus="autofocus" />
										<span class="help-block"><form:errors
												path="confirmPassword" cssClass="error" /></span>
									</div>
								</div>
								<div class="action">
									<center><button type="submit" class="btn btn-primary">SIGN UP</button></center>
								</div>
							</div>
						</div>

						<div class="already">
							<p>Have an account already?</p>
							<a href="${pageContext.request.contextPath }/login">Login</a>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>