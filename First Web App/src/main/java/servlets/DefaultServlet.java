package servlets;

import Templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 4/16/2016.
 */
public class DefaultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariableMap(request);
        pageVariables.put("message", "");

        response.getWriter().println(getHtmlPage("index.html", pageVariables));

        response.setContentType("text/html;charset=urf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariableMap(request);
        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");
        if (message == null || message == "") {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        pageVariables.put("message", message == null ? "" : message);
        response.getWriter().println(getHtmlPage("index.html", pageVariables));


    }

    private String getHtmlPage(String fileName, Map<String, Object> data) {
        return PageGenerator.instance().getPage(fileName, data);
    }

    private Map<String, Object> createPageVariableMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<String, Object>();

        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("params", request.getParameterMap().toString());

        return pageVariables;
    }
}
