<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Online Voting System - Dashboard</title>

<link rel="stylesheet"
	href="<c:url value="/resources/css/vendor/bootstrap.min.css" />">


<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/vendor/dataTables.bootstrap4.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/app/style.css"/>"
	rel="stylesheet" type="text/css">
</head>

<body>

	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<!-- Logo -->
					<div class="logo">
						<h1>
							<a href="${pageContext.request.contextPath }">Online Voting
								System</a>
						</h1>
					</div>
				</div>
				<div class="col-md-5">
					<div class="row"></div>
				</div>
				<div class="col-md-2">
					<div class="navbar navbar-inverse" role="banner">
						<nav
							class="collapse navbar-collapse bs-navbar-collapse navbar-right"
							role="navigation">
						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">My Account <b class="caret"></b></a>
								<ul class="dropdown-menu animated fadeInUp">
									<li><a
										href="${pageContext.request.contextPath }/manage-profile">Profile</a></li>
									<li><a data-toggle="modal" data-target="#log_out_modal">Logout</a></li>
								</ul></li>
						</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="page-content">
		<div class="row">
			<div class="col-md-2">
				<div class="sidebar content-box"
					style="display: block; position: fixed;">
					<ul class="nav">
						<!-- Main menu -->
						<li class="current"><a
							href="${pageContext.request.contextPath }/dashboard"><i
								class="glyphicon glyphicon-home"></i> Dashboard</a></li>
						<li><a
							href="${pageContext.request.contextPath }/create-election"><i
								class="glyphicon glyphicon-pencil"></i> New Election</a></li>
						<li><a
							href="${pageContext.request.contextPath }/manage-profile"><i
								class="glyphicon glyphicon-list"></i> Profile</a></li>
						<li><a href="${pageContext.request.contextPath }/app-tour"><i
								class="glyphicon glyphicon-record"></i> App Tour</a></li>


					</ul>
				</div>
			</div>
			<div class="col-md-10" style="float: right;">
				<input type="hidden" id="site_ulr"
					value="${pageContext.request.contextPath }">
				<div class="row">
					<div class="col-md-12">
						<div class="content-box-header">
							<div class="panel-title">Manage Profile</div>


						</div>
						<div class="content-box-large box-with-header content-area">
							<c:if test="${param.success!=null }">
								<div class="alert alert-success">
									<strong>Success! Profile updated successfully !.</strong>
								</div>
							</c:if>

							<c:if test="${param.error!=null }">
								<div class="alert alert-danger">
									<strong>Something went wrong ! Try again</strong>
								</div>
							</c:if>


							<form:form class="form-horizontal"
								action="${pageContext.request.contextPath }/updateUser"
								method="POST" modelAttribute="newUser">
								
								<form:hidden path="userName" />
								<div class="form-group">
									<label for="username" class="col-sm-3 control-label">User
										Name</label>
									<div class="col-sm-9">
										<label class="form-control disabled" autofocus="autofocus">${newUser.userName }</label>
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


								<div class="form-group">
									<div class="col-sm-9 col-sm-offset-3">
										<button type="submit" class="btn btn-primary btn-block">Update</button>
									</div>
								</div>
							</form:form>

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	</div>


	<!-- Logout Modal-->
	<div class="modal fade" id="log_out_modal" tabindex="-1" role="dialog"
		aria-labelledby="log_out_modal_label" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="log_out_modal_label">Ready to
						Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">�</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<form:form action="${pageContext.request.contextPath }/logout"
						method="POST">
						<input type="submit" class="btn btn-primary" value="Logout">
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="<c:url value="/resources/js/vendor/jquery.dataTables.js" />"></script>
	<script
		src="<c:url value="/resources/js/vendor/dataTables.bootstrap4.js" />"></script>
	<script src="<c:url value="/resources/js/vendor/tables.js" />"></script>

</body>
</html>