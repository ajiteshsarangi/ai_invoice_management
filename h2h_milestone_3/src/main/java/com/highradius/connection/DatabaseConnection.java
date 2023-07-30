package com.highradius.connection;

import java.util.ArrayList;
import java.util.List;

import com.highradius.model.*;

import java.sql.*;

public class DatabaseConnection {

	static public ArrayList<Invoice> invoicesLists = new ArrayList<>();
	
	static final String URL = "jdbc:mysql://localhost:3306/invoices";
	
	static final String USER = "root";
	
	static final String PASS = "root";

	public static Connection connect() throws SQLException {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			if (con == null) {
				System.out.println("Connection cannot be established");
			}
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	

	public List<Invoice> getInvoices() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		try {
			System.out.println("Fetching records from the database...");
			String query = "SELECT * FROM h2h_oap WHERE Sl_no <= 10000";
			conn = connect();
			
			stmt = conn.createStatement();
			
			invoicesLists.clear();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				Invoice inv = new Invoice();
				inv.setSlNo(rs.getInt(1));
				inv.setCustomerOrderId(rs.getInt(2));
				inv.setSalesOrg(rs.getInt(3));
				inv.setDistributionChannel(rs.getString(4));
				inv.setCompanyCode(rs.getInt(8));
				inv.setOrderCreationDate(rs.getString(9));
				inv.setOrderCurrency(rs.getString(15));
				inv.setCustomerNumber(rs.getInt(17));
				inv.setAmountUSD(rs.getDouble(18));
				inv.setOrderAmount(rs.getDouble(13));
				invoicesLists.add(inv);
				
				System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3) + "  " + rs.getString(4)
						+ "  " + rs.getInt(8) + "  " + rs.getString(9) + "  " + rs.getString(13) + "  "
						+ rs.getString(15) + "  " + rs.getInt(17) + "  " + rs.getString(18));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
				System.out.println("ALL THE DATA IS FETCHED");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return invoicesLists;
	}

	public void addInvoice(Invoice invoice) {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			if (con == null) {
				System.out.println("Connection cannot be established");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			
			String query = "INSERT INTO h2h_oap (Sl_no, customer_order_id, sales_org, distribution_channel, company_code, order_creation_date, order_currency, customer_number, amount_in_usd, order_amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			
			PreparedStatement statement = con.prepareStatement(query);

			
			statement.setInt(1, invoice.getSlNo());
			statement.setInt(2, invoice.getCustomerOrderId());
			statement.setInt(3, invoice.getSalesOrg());
			statement.setString(4, invoice.getDistributionChannel());
			statement.setInt(5, invoice.getCompanyCode());
			statement.setString(6, invoice.getOrderCreationDate());
			statement.setString(7, invoice.getOrderCurrency());
			statement.setInt(8, invoice.getCustomerNumber());
			statement.setDouble(9, invoice.getAmountUSD());
			statement.setDouble(10, invoice.getOrderAmount());

			
			statement.executeUpdate();
			System.out.println("INSERTED....");
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		invoicesLists.add(invoice);
	}

	public boolean deleteInovice(int customerOrderId) {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			if (con == null) {
				System.out.println("Connection cannot be established");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		

		try {
			
			String sql = "DELETE FROM h2h_oap WHERE customer_order_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, customerOrderId);
			int rowsAffected = statement.executeUpdate();
			statement.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateInvoice(int customerOrderId, Invoice inv) {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			if (con == null) {
				System.out.println("Connection cannot be established");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			
			String sql = "UPDATE h2h_oap SET order_currency = ?, company_code = ?, distribution_channel = ? WHERE customer_order_id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, inv.getOrderCurrency());
			statement.setInt(2, inv.getCompanyCode());
			statement.setString(3, inv.getDistributionChannel());
			statement.setInt(4, customerOrderId);

			
			int rowsUpdated = statement.executeUpdate();

			
			statement.close();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}

}
