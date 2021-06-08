package pk1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private void addAccount(String username, String password) {
    	String databaseUrl = "jdbc:mysql://localhost:3306/jsplogin?user=" + "root" + "&password=" + "1234";
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseUrl);
            System.out.println("Connected to database");
            PreparedStatement st = conn.prepareStatement("INSERT INTO account VALUES (?, ?)");
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DO post run");
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
        if (req.getParameter("return") != null) 
        	req.getRequestDispatcher("index.jsp").forward(req,resp);
        else if (req.getParameter("signin")!= null) {
        	addAccount(username, password);
        }	
    }
}