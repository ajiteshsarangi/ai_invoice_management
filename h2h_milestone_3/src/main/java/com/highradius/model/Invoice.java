package com.highradius.model;

public class Invoice {
	private int slNo;
	private int customerOrderId;
	private int salesOrg;
	private String distributionChannel;
	private int companyCode;
	private String orderCreationDate;
	private String orderCurrency;
	private int customerNumber;
	private double amountUSD;
	private double orderAmount;

	// Parameterized Constructor
	public Invoice(int SlNo, int customerOrderId, int salesOrg, String distributionChannel, int companyCode,
			String orderCreationDate, String orderCurrency, int customerNumber, double amountUSD, double orderAmount) {
		this.slNo = SlNo;
		this.customerOrderId = customerOrderId;
		this.salesOrg = salesOrg;
		this.distributionChannel = distributionChannel;
		this.companyCode = companyCode;
		this.orderCreationDate = orderCreationDate;
		this.orderCurrency = orderCurrency;
		this.customerNumber = customerNumber;
		this.amountUSD = amountUSD;
		this.orderAmount = orderAmount;
	}

	public Invoice() {

	}
	// Getters and Setters For each variable.
	public int getSlNo() {
		return slNo;
	}

	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	public int getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(int customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public int getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(int salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getDistributionChannel() {
		return distributionChannel;
	}

	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}

	public int getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}

	public String getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(String orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public double getAmountUSD() {
		return amountUSD;
	}

	public void setAmountUSD(double amountUSD) {
		this.amountUSD = amountUSD;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
}
