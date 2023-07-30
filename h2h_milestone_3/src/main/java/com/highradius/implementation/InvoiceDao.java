package com.highradius.implementation;

import java.util.List;

import com.highradius.model.Invoice;

public interface InvoiceDao {
	public List<Invoice> getinvoice();

	public void insertInvoice(Invoice inv);

	public boolean updateInvoice(int customerOrderId, Invoice inv);

	public boolean deleteInvoice(int customerOrderId);

}
