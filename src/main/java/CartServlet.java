import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            DisplayCart(request, response);
        } else {
            if (action.equalsIgnoreCase("buy")) {
                Buy(request, response);
            } else if (action.equalsIgnoreCase("remove")) {
                Remove(request, response);
            } else if (action.equalsIgnoreCase("update")) {
                Update(request, response);
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<form method = \"get\" action=\"ProductServlet\"><input type=\"submit\" value = \"Products\"> </form>");
        out.println("<form method = \"post\" action=\"LogoutServlet\"><input type=\"submit\" value = \"Log Out\"> </form>");
    }

    protected void DisplayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void Remove(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"), cart);
        cart.remove(index);

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }

    protected void Update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart");

        int index = isExisting(request.getParameter("id"), cart);
        Integer upq = Integer.valueOf(request.getParameter("upq"));
        cart.get(index).setQuantity(upq);

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }

    protected void Buy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Product product = new Product();

        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            ArrayList<Cart> cart = new ArrayList<Cart>();

            cart.add(new Cart(new Product(request.getParameter("id"),request.getParameter("name"),request.getParameter("price"),request.getParameter("image")), 1));

            session.setAttribute("cart", cart);
        } else {
            ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart");

            int index = isExisting(request.getParameter("id"), cart);
            if (index == -1) {
                byte [] b = request.getParameter("image").getBytes(StandardCharsets.UTF_8);
                ByteArrayInputStream bis = new ByteArrayInputStream(b);
                BufferedImage bImage2 = ImageIO.read(bis);
                cart.add(new Cart(new Product(request.getParameter("id"),request.getParameter("name"),request.getParameter("price"),request.getParameter("image")), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("cart.jsp");
    }

    private int isExisting(String id, ArrayList<Cart> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
