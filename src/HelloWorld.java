import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet {

	private ServletConfig paramServletConfig;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><body><h1>Hola mundo</h1>" + new Date().toString()
				+ "</body></html>");
	}

	@Override
	public void init(ServletConfig paramServletConfig) throws ServletException {
		this.paramServletConfig = paramServletConfig;
	}

	@Override
	public ServletConfig getServletConfig() {
		return paramServletConfig;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {
	}

}
