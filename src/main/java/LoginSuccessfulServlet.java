import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LoginSuccessfulServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = null;
        HttpSession session = request.getSession();
        ArrayList<Cart> cart = new ArrayList<Cart>();

        if (session.getAttribute("currentUser") == null)
        {
            response.sendRedirect("index.html");
        }
        else
        {
            user = session.getAttribute("currentUser").toString();
        }

        PrintWriter out = response.getWriter();
        out.println("<h1>Welcome " + user + "</h1>");
        out.println("<form method = \"get\" action=\"ProductServlet\"><input type=\"submit\" value = \"Products\"> </form>");
        out.println("<form method = \"get\" action=\"CartServlet\"><input type=\"submit\" value = \"Your Cart\"> </form>");
        out.println("<form method = \"post\" action=\"LogoutServlet\"><input type=\"submit\" value = \"Log Out\"> </form>");
    }
}