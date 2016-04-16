package firstWebApp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;


/**
 * Created by Andrey on 4/16/2016.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        Server server = new Server(8080);
        DefaultServlet servlet = new DefaultServlet();
        MirrorRequestServlet mirrorRequestServlet = new MirrorRequestServlet();

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.addServlet(new ServletHolder(mirrorRequestServlet),"/mirror");
        handler.addServlet(new ServletHolder(servlet),"/*");


        server.setHandler(handler);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
