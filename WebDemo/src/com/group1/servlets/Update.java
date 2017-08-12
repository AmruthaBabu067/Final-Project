package com.group1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group1.data.DbMgr;

/**
 * Servlet implementation class Update
 */
@WebServlet(name = "Update", urlPatterns = { "/Update" })
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Email = request.getParameter("Email");
		String Country = request.getParameter("Country");
		
		String userName = "Amrutha";
		HttpSession session =request.getSession();
		userName = (String) session.getAttribute("userName") !=null ? (String) session.getAttribute("userName"): userName;
		
		
		ServletContext ctx = getServletContext();
		DbMgr dbMgr = (DbMgr) ctx.getAttribute("DbMgr");

		Connection con = dbMgr.getConnection();
		String insertQuery = "UPDATE USERS SET email=?, country=?, WHERE user_name=?";
		boolean success = false;
		try {
			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setString(1, Email);
			ps.setString(2, Country);
			ps.setString(3, userName);
			
			
			success = ps.execute();
			
		doGet(request, response);
		
		
	} catch (SQLException e){
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
RequestDispatcher rd = ctx.getRequestDispatcher("/profile.html");
PrintWriter out = response.getWriter();
out.println("<font color = green> User Profile Updated!"+"</font>");
rd.include(request, response);
	}

}
