import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> productArray = new ArrayList<>();

        PrintWriter out = response.getWriter();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","user");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from products");

            while(rs.next()) {
                productArray.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            con.close();
        }
        catch(Exception e) {
            out.println(e);
        }

        request.setAttribute("productArray", productArray);
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
