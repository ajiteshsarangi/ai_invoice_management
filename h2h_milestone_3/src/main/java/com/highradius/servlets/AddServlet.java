package com.highradius.servlets;
import com.highradius.implementation.*;
import com.highradius.connection.*;
import com.highradius.model.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class add
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection databaseConnection = new DatabaseConnection();
	InvoiceDaoImpl inv = new InvoiceDaoImpl(databaseConnection);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		


	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int slNo = 0;
        int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
        int salesOrg = Integer.parseInt(request.getParameter("salesOrg"));
        String distributionChannel = request.getParameter("distributionChannel");
    	int companyCode = Integer.parseInt(request.getParameter("companyCode"));
    	String orderCreationDate = request.getParameter("orderCreationDate");
    	String orderCurrency = request.getParameter("orderCurrency");
    	int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
    	double amountUSD = Double.parseDouble(request.getParameter("amountInUSD"));

    	double orderAmount = 0;
    	Invoice invoice = new Invoice(slNo, customerOrderId, salesOrg, distributionChannel, companyCode, orderCreationDate, orderCurrency, customerNumber, amountUSD, orderAmount);
    	inv.insertInvoice(invoice);
    	
    	response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");

        Gson gson = new Gson();
        String jsonResponse = gson.toJson("Invoice added successfully");
        
      
        response.getWriter().write(jsonResponse.toString());

	}

}
