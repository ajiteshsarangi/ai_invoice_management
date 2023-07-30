package com.highradius.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.highradius.connection.DatabaseConnection;
import com.highradius.implementation.*;
/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public InvoiceDaoImpl inv;

	@Override
    public void init() throws ServletException {
//        super.init();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        inv = new InvoiceDaoImpl(databaseConnection);
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String[] inputString = request.getParameterValues("customerOrderId");
		String[] customerOrderIds = inputString[0].split(",");
		Gson gson = new Gson();
	    JsonObject jsonResponse = new JsonObject();
	    
	    for (String customerId : customerOrderIds) {
	        int id;
	        try {
	            id = Integer.parseInt(customerId);
	        } catch (NumberFormatException e) {
	            jsonResponse.addProperty(customerId, "Invalid customerOrderId format");
	            continue;
	        }
	        
	        boolean isDeleted = inv.deleteInvoice(id);
	        
	        if (isDeleted) {
	            jsonResponse.addProperty(customerId, "Invoice Deleted successfully");
	        } else {
	            jsonResponse.addProperty(customerId, "Invoice Deleted Unsuccessfully");
	        }
	    }
		
		response.setContentType("application/json");
		

		response.getWriter().write(gson.toJson(jsonResponse));

	}

}
