<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form:form class="form-horizontal" 
				action="${pageContext.request.contextPath }/saveElectionBasicInfo"
				method="POST"
				modelAttribute="positionListModel">
	<div class="panel panel-success" id="election-general">
		<div class="panel-heading">
			General Information 
		</div>
		<div class="panel-body">
				
				<div class="form-group">
				<label for="election-name" class="col-sm-3 control-label">Election Name</label>
				<div class="col-sm-9">
				<label class="form-control">${positionListModel.electionName}</label>
						<span class="help-block"></span>
				</div>
				<form:hidden path="electionName"/>
			</div>
			<div class="form-group">
				<label for="number-of-position" class="col-sm-3 control-label">Number of Position</label>
				<div class="col-sm-9">
				<label class="form-control">${positionListModel.numOfPosition}</label>
				</div>
				<form:hidden path="numOfPosition"/>
			</div>
			
				
		</div>	
		</div>	
				
<c:forEach  items="${positionListModel.positions }" var="curPosition" varStatus="key">
<div class="panel panel-default" id="election-info">
		<div class="panel-heading">
			Position <span class="position_id">${key.index+1}</span>
			<form:hidden path="positions[${key.index}].positionId"/>
		</div>
		<div class="panel-body">

			<div class="form-group">
				<label for="election-name" class="col-sm-3 control-label">Position
					Name</label>
				<div class="col-sm-9">
				<form:input path="positions[${key.index}].positionName" placeholder="Election Name" cssClass="form-control" required="required"/>
					 <span class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="number-of-position" class="col-sm-3 control-label">Number
					of candidate</label>
				<div class="col-sm-9">
				   <input type="number" name="positions[${key.index}].noOfCandidate" placeholder="Number of Position" class="form-control" required="required"> 
					 <span class="help-block"></span>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
<hr>
<div style="display:inline-block;float: right;">
<a href="${pageContext.request.contextPath }/create-election" ><input type="button" class="btn btn-danger" value="Cancel"/></a>
&nbsp;<input type="submit" value="Next" class="btn btn-success">
</div>

</form:form>	
</body>
</html>