<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/view_templates/head_content_default.jsp"/>
</head>
<body>
	<h1>Hello, you are in employee listing page</h1>
	
	<h1>Total employee count: <s:property value="employeeCount"/></h1>
	
	<h1>Employees List</h1>
	<table>
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>NRIC</td>
			<td>Remarks</td>
			<td>Delete</td>
		</tr>
		<s:iterator value="employees">
			<tr>
				<td><s:property value="id"/></td>
				<td><s:property value="fullName" escape="false"/></td>
				<td><s:property value="nric"/></td>
				<td><s:property value="remarks" escape="false"/></td>
				<td>
					<s:form namespace="/employee" action="delete" method="post">
						<s:token/>
						<s:hidden name="id" value="%{id}"/>
						<s:submit value="Delete"/>
					</s:form>
				</td>			
			</tr>
		</s:iterator>
	</table>
	
	<s:url id="createFormUrl" namespace="/employee" action="create_form"/>
	<h1><s:a href="%{createFormUrl}">Create New Employee</s:a></h1>
</body>
</html>