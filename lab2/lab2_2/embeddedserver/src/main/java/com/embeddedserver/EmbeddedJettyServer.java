package com.embeddedserver;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
 
public class EmbeddedJettyServer {
 
    public static void main(String[] args) throws Exception {
         
        Server server = new Server(8680);       
         
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
                 
        servletHandler.addServletWithMapping(HelloServlet.class, "/");
         
        server.start();
        server.join();
 
    }
     
    public static class HelloServlet extends HttpServlet 
    {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            String username = request.getParameter("username");
            
            if(username == null){
                throw new NullPointerException("Username não encontrado!");
            }
            else{
                try {
                    response.getWriter().println("<html>");
                    response.getWriter().println("<head>");
                    response.getWriter().println("<title>IES Practical Classes</title>");
                    response.getWriter().println("</head>");
                    response.getWriter().println("<body>");
                    response.getWriter().println("<h2>Hello " + username + "</h2>");
                    response.getWriter().println("<img src='data:image/jpeg;base64,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'/>");
                    response.getWriter().println("</body>");
                    response.getWriter().println("</html>");

                } finally {
                    response.getWriter().close();
                }

            }
        } 
    }
 }