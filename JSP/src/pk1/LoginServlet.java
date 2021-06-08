package pk1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private boolean checkLogin(String username, String password) {
    	String databaseUrl = "jdbc:mysql://localhost:3306/jsplogin?user=" + "root" + "&password=" + "1234";
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseUrl);
            System.out.println("Connected to database");
            PreparedStatement st = conn.prepareStatement("SELECT * FROM account WHERE username= ? AND password = ?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet res = st.executeQuery();
            if (res.next()) 
                return true;
            else 
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DO post run");
        String username = req.getParameter("username");
    	String password = req.getParameter("password");
        if (req.getParameter("return") != null) {
        	System.out.println("Return main page");
        	req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
        else if (req.getParameter("login")!= null) {
        	if (checkLogin(username, password)) {
        		req.getRequestDispatcher("calculator.jsp").forward(req,resp);
        	} else {
        		PrintWriter out = resp.getWriter();
        		out.println("<h1>" + "Login Fail" + "</h1>");
        	}
        }
    }
}