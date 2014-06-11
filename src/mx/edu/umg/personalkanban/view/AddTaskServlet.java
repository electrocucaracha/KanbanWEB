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
