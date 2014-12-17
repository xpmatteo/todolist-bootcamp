package it.xpug.toolkit.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.ServletHandler;

public class ReusableJettyApp {

	private Server server;
	private final Class<? extends HttpServlet> servletClass;

	public ReusableJettyApp(Class<? extends HttpServlet> servlet) {
		this.servletClass = servlet;
	}

	public void start(int port, String resourceBase) {
		server = new Server(port);
		try {
			HandlerList handlers = new HandlerList();
			handlers.setHandlers(new Handler[] { resourceHandler(resourceBase),
					servletHandler(), new DefaultHandler() });
			server.setHandler(handlers);
			server.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected ResourceHandler resourceHandler(String resourceBase) {
		ResourceHandler resourceHandler = new ResourceHandler() {
			@Override
			public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
				if (request.getPathInfo().equals("/")) {
					((Request) request).setHandled(false);					
				} else {
					super.handle(target, request, response, dispatch);
				}
			}
		};
		resourceHandler.setResourceBase(resourceBase);		
		return resourceHandler;
	}

	protected ServletHandler servletHandler() {
		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(servletClass, "/");
		return servletHandler;
	}

	public void shutdown() throws InterruptedException {
		try {
			server.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
