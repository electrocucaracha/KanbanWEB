package mx.edu.umg.personalkanban.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.edu.umg.personalkaban.Program;
import mx.edu.umg.personalkaban.model.Category;
import mx.edu.umg.personalkaban.model.State;
import mx.edu.umg.personalkaban.model.Task;

public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("	<head>");
		writer.println("		<title>Add task</title>");
		writer.println("	</head>");
		writer.println("	<body>");
		writer.println("		<form method=\"POST\">");
		writer.println("			<table>");
		writer.println("				<tr>");
		writer.println("					<td>Title:</td>");
		writer.println("					<td><input type=\"text\" name=\"title\" /></td>");
		writer.println("				</tr>");
		writer.println("				<tr>");
		writer.println("					<td>Description:</td>");
		writer.println("					<td><input type=\"text\" name=\"description\" /></td>");
		writer.println("				</tr>");
		writer.println("				<tr>");
		writer.println("					<td>State:</td>");
		writer.println("					<td>");
		writer.println("						<select name=\"state\">");
		for (State state : State.values()) {
			writer.println("							<option value=\"" + state.name()
					+ "\">" + state.toString() + "</option>");
		}
		writer.println("						</select>");
		writer.println("					</td>");
		writer.println("				</tr>");
		writer.println("				<tr>");
		writer.println("					<td>Category:</td>");
		writer.println("					<td><input type=\"text\" name=\"category\" /></td>");
		writer.println("				</tr>");
		writer.println("				<tr>");
		writer.println("					<td>Priority:</td>");
		writer.println("					<td><input type=\"text\" name=\"priority\" /></td>");
		writer.println("				</tr>");
		writer.println("				<tr>");
		writer.println("					<td>Owner:</td>");
		writer.println("					<td><input type=\"text\" name=\"owner\" /></td>");
		writer.println("				</tr>");
		writer.println("				<tr>");
		writer.println("					<td>Due date:</td>");
		writer.println("					<td><input type=\"text\" name=\"due_date\" value=\""
				+ new Date() + "\"/></td>");
		writer.println("				</tr>");
		writer.println("				<tr>");
		writer.println("					<td colspan=\"2\"><input type=\"submit\" value=\"Add\" /></td>");
		writer.println("				</tr>");
		writer.println("			</table>");
		writer.println("		</form>");
		writer.println("	</body>");
		writer.println("</html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Task task = new Task();

		task.setTitle(request.getParameter("title"));
		task.setDescription(request.getParameter("description"));
		task.setOwner(request.getParameter("owner"));
		task.setCategory(new Category(request.getParameter("category")));

		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEE MMM d HH:mm:ss zzz yyyy");
		try {
			task.setDueDate(formatter.parse(request.getParameter("due_date")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			task.setState(State.valueOf(request.getParameter("state")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			task.setPriority(Short.parseShort(request.getParameter("priority")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		task.setCreateDate(new Date());

		Program.dashboard.add(task);

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><body>" + task.toHtml() + "</body></html>");
	}
}
