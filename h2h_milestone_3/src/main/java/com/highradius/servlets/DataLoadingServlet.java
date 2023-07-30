package com.highradius.servlets;

import com.highradius.implementation.*;
import com.highradius.connection.*;
import com.highradius.model.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/dataloading")
public class DataLoadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection databaseConnection;
	public InvoiceDaoImpl inv;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataLoadingServlet() {
		super();
		
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		databaseConnection = new DatabaseConnection();
		inv = new InvoiceDaoImpl(databaseConnection);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

		List<com.highradius.model.Invoice> invoices = inv.getinvoice();
		
		Gson gson = new Gson();

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");

		String list = gson.toJson(invoices);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		
		response.getWriter().write(list);

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
