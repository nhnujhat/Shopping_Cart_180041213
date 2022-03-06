import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String pw = request.getParameter("pw");
        ArrayList<Cart> cart = new ArrayList<Cart>();

        if(username.equals("nujhat") && pw.equals("111"))
        {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", username);
            session.setAttribute("cart", cart);
            session.setMaxInactiveInterval(30*60);

            RequestDispatcher rd = request.getRequestDispatcher("LoginSuccessfulServlet");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("index.html");
        }
    }
}