<%@ page language="java" contentType="text/html"%>
<%@ page import="java.util.Date"%>
<%@ page import="mx.edu.umg.personalkaban.model.State"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add task</title>
<%@ include file="Copyright.jspf"%>
<script>
	function validate() {
		var title = document.forms["task_form"]["title"].value;
		if (!title) {
			alert("Title empty!!!");
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<form name="task_form" method="POST" action="AddTaskServlet"
		onsubmit="return validate()">
		<table>
			<tr>
				<td>Title:</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="description" /></td>
			</tr>
			<tr>
				<td>State:</td>
				<td><select name="state">
						<%-- State values added dynamically --%>
						<%
							for (State state : State.values()) {
						%>
						<option value="<%=state.name()%>">
							<%=state%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><input type="text" name="category" /></td>
			</tr>
			<tr>
				<td>Priority:</td>
				<td><input type="text" name="priority" /></td>
			</tr>
			<tr>
				<td>Owner:</td>
				<td><input type="text" name="owner" /></td>
			</tr>
			<tr>
				<td>Due date:</td>
				<td><input type="text" name="due_date"
					value="<%=new Date().toString()%>" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>
		</table>
	</form>
</body>
</html>