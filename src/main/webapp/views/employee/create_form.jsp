<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<s:include value="/view_templates/head_content_default.jsp"/>
</head>
<body>
	<s:form action="create" namespace="/employee" method="post" validate="true">
		<s:token/>
		<s:textfield name="fullName" label="Full Name" required="true"/>
		<s:textfield name="nric" label="NRIC" required="true"/>
		<sjr:ckeditor name="remarks" label="Remarks" rows="5" cols="50" required="true"/>
		<s:submit value="Submit"/>
		<s:reset value="Reset"/>
	</s:form>
	
	<s:url id="listEmployeeUrl" namespace="/employee" action="list"/>
	<h1><s:a href="%{listEmployeeUrl}">List All Employees</s:a></h1>	
</body>
</html>