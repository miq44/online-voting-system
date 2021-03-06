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
<link href="<c:url value="/resources/css/vendor/dataTables.bootstrap4.css" />"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/vendor/fancy_radio.css" />"
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
							<a href="${pageContext.request.contextPath }">Online Voting System</a>
						</h1>
					</div>
				</div>
				<div class="col-md-5">
					<div class="row"></div>
				</div>
				<div class="col-md-2">
					
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
						<li class="current"><a href="${pageContext.request.contextPath }/my-vote/${token}"><i
								class="glyphicon glyphicon-home"></i> My Vote</a></li>
						<li><a href="${pageContext.request.contextPath }/register"><i
								class="glyphicon glyphicon-list"></i> Sign up</a></li>


					</ul>
				</div>
			</div>
			<div class="col-md-10" style="float: right;">
				<input type="hidden" id="site_ulr"
					value="${pageContext.request.contextPath }">
				<div class="row">
					<div class="col-md-12">
						<div class="content-box-header">
							<div class="panel-title">My Elections</div>


						</div>
						<div class="content-box-large box-with-header content-area">
							<form:form class="form-horizontal"
			action="${pageContext.request.contextPath }/saveVote" method="POST"
			modelAttribute="votesModel">
            <form:hidden path="voterToken"/>
			<c:forEach items="${map }" var="candidates" varStatus="testKey">
				<div class="panel panel-success" id="candidate_general">
					<div class="panel-heading">${candidates.key }</div>
					<div class="panel-body">
						<div class="frb-group">
						<form:hidden path="votes[${ testKey.index}].positionId" value="${ candidates.value[0].positionId}"/>
							<c:forEach items="${candidates.value }" var="curCandidate"
								varStatus="loop">


								<div class="frb frb-success">

<form:radiobutton  path="votes[${ testKey.index}].candidateId" value="${ curCandidate.candidateId}" id="radio-button-${testKey.index}${ loop.index }" required="required"/>
                             <label for="radio-button-${testKey.index}${ loop.index }">


										<div class="row flex">
											<div class="col-md-2"></div>
											<div class="col-md-7">
												<div class="vertical-center" style="top: 50%;">
													<div class="form-group">
														<label for="election-name" class="col-sm-4 control-label">Name</label>
														<div class="col-sm-8">
															<label class="form-control"
																style="background-color: #D3D3D3;">${ curCandidate.candidateName}</label>
															<span class="help-block"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="election-name" class="col-sm-4 control-label">Email
															Id</label>
														<div class="col-sm-8">
															<label class="form-control"
																style="background-color: #D3D3D3;">${ curCandidate.candidateEmail}</label>
														</div>
													</div>
												</div>
											</div>
											<div class="col-md-3">
												<div class="vertical-center right">
													<img class="candidate-photo"
														src="<c:url value="/images/${ curCandidate.candidatePhoto}" />">
												</div>

											</div>
										</div>



									</label>
								</div>



							</c:forEach>
						</div>
					</div>
				</div>

			</c:forEach>
			<input type="submit" class="btn btn-success" value="Submit"
				style="float: right; margin-bottom: 100px;">
		</form:form>
									
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<c:url value="/resources/js/vendor/jquery.dataTables.js" />"></script>
	<script src="<c:url value="/resources/js/vendor/dataTables.bootstrap4.js" />"></script>
	<script src="<c:url value="/resources/js/vendor/tables.js" />"></script>
	<script src="<c:url value="/resources/js/app/app.js" />"></script>
</body>
</html>