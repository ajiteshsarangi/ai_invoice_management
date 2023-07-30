package com.highradius.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.google.gson.Gson;
import com.highradius.connection.DatabaseConnection;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.*;

/**
 * Servlet implementation class editData
 */
@WebServlet("/editData")
public class editData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public InvoiceDaoImpl inv;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editData() {
        super();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        inv = new InvoiceDaoImpl(databaseConnection);
        
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

		boolean isUpdated = false;
		List<com.highradius.model.Invoice> invoices = inv.getinvoice();
		int customerId = Integer.parseInt(request.getParameter("customerOrderId"));
		String orderCurrency = request.getParameter("orderCurrency");
        int companyCode = Integer.parseInt(request.getParameter("companyCode"));
        String distributionChannel = request.getParameter("distributionChannel");

        for (Invoice invoice : invoices) {
            
            if (invoice.getCustomerOrderId() == customerId) {
               
                invoice.setOrderCurrency(orderCurrency);
                invoice.setCompanyCode(companyCode);
                invoice.setDistributionChannel(distributionChannel);
                isUpdated = inv.updateInvoice(customerId, invoice);
            }
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        
        Gson gson = new Gson();
		String res;
		if(isUpdated) {
			res = "Invoice Edited successfully";
		}
		else {
			res = "Invoice Edited Unsuccessfully";
		}
		String jsonResponse = gson.toJson(res);
                
   
        response.getWriter().write(jsonResponse.toString());

	}

}
