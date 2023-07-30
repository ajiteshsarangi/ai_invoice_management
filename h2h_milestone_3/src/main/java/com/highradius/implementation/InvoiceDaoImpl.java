package com.highradius.implementation;

import java.util.ArrayList;
import java.util.List;

import com.highradius.model.Invoice;
import com.highradius.connection.*;
import com.highradius.connection.DatabaseConnection;

public class InvoiceDaoImpl implements InvoiceDao {

	
	private DatabaseConnection databaseConnection;

	public InvoiceDaoImpl(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	@Override
	public List<Invoice> getinvoice() {
		return databaseConnection.getInvoices();
	}

	@Override
	public void insertInvoice(Invoice inv) {
		databaseConnection.addInvoice(inv);

	}

	@Override
	public boolean updateInvoice(int customerOrderId, Invoice inv) {
		
		return databaseConnection.updateInvoice(customerOrderId, inv);

	}

	@Override
	public boolean deleteInvoice(int customerOrderId) {
		
		return databaseConnection.deleteInovice(customerOrderId);
	}

}
