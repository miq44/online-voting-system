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
					<div class="navbar navbar-inverse" role="banner">
						<nav
							class="collapse navbar-collapse bs-navbar-collapse navbar-right"
							role="navigation">
						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">My Account <b class="caret"></b></a>
								<ul class="dropdown-menu animated fadeInUp">
									<li><a href="${pageContext.request.contextPath }/manage-profile">Profile</a></li>
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
						<li class="current"><a href="${pageContext.request.contextPath }/dashboard"><i
								class="glyphicon glyphicon-home"></i> Dashboard</a></li>
						<li><a href="${pageContext.request.contextPath }/create-election"><i
								class="glyphicon glyphicon-pencil"></i> New Election</a></li>
						<li><a href="${pageContext.request.contextPath }/manage-profile"><i
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
							<div class="panel-title">My Elections</div>


						</div>
						<div class="content-box-large box-with-header content-area">
							
									<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="election_table"
										width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>Name</th>
												<th>Total Position</th>
												<th>Total Voter</th>
												<th>Manage Voter</th>
												<th>Candidates</th>
												<th>Status</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>Name</th>
												<th>Total Position</th>
												<th>Total Voter</th>
												<th>Manage Voter</th>
												<th>Candidates</th>
												<th>Status</th>
											</tr>
										</tfoot>
										<tbody>

											<c:forEach items="${ electionModels}" var="election"
												varStatus="key">
												<tr>
													<input type="hidden" value="${election.electionId }"
														class="election-id" />
													<td>${election.electionName }</td>
													<td>${election.numOfPosition }</td>
													<td>${election.numOfVoter }</td>
													<td><c:if test="${election.electionStatus==0}">
															<a
																href="${pageContext.request.contextPath }/addVoter/${election.electionId }">Add</a>  | 
                  <a
																href="${pageContext.request.contextPath }/manageVoter/${election.electionId }">Manage</a>
														</c:if> <c:if test="${election.electionStatus>0}">
															<a
																href="${pageContext.request.contextPath }/manageVoter/${election.electionId }">View
																Voters</a>
														</c:if></td>
													<td><a
														href="${pageContext.request.contextPath }/viewCandidates/${election.electionId }">View
															Candidates</a></td>
													<td><c:if test="${election.electionStatus==0}">
															<button class="begin-voting btn btn-success"
																data-toggle="modal" data-target="#begin_election">Begin
																Voting</button>
														</c:if> <c:if test="${election.electionStatus==1}">
															<button class="end-voting btn btn-danger"
																data-toggle="modal" data-target="#end_election">End
																Voting</button>
														</c:if> <c:if test="${election.electionStatus==2}">
															<a
																href="${pageContext.request.contextPath }/my-vote-result/${election.electionToken }">View
																Results</a>
														</c:if></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">Select "Logout" below if you are
						ready to end your current session.</div>
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
		<!-- Begin Election Modal-->
		<div class="modal fade" id="begin_election" tabindex="-1" role="dialog"
			aria-labelledby="begin_election" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Ready to
							Start?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">Are you sure you want to begin this election ? You won't be able to add any new voter once start voting. </div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancel</button>
						<form:form action="${pageContext.request.contextPath }/beginElection"
							method="POST">
							<input type="hidden" id="election_id_to_begin_election" name="electionId"/>
							<input type="submit" class="btn btn-success" value="Begin">
						</form:form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- End Election Modal-->
		<div class="modal fade" id="end_election" tabindex="-1" role="dialog"
			aria-labelledby="end_election" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Ready to
							finish the election?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">Are you sure you want to end this election ? Voter will no longer be able to cast their votes. </div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancel</button>
						<form:form action="${pageContext.request.contextPath }/endElection"
							method="POST">
							<input type="hidden" id="election_id_to_end_election" name="electionId"/>
							<input type="submit" class="btn btn-success" value="End">
						</form:form>
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