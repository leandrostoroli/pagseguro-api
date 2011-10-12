package br.com.tagfy.pagseguro.api.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
	
	public static final Integer TYPE_PAYMENT = 1;
	public static final Integer TYPE_TRANSFER = 2;
	public static final Integer TYPE_FUND_ADDITION = 3;
	public static final Integer TYPE_WITHDRAW = 4;
	public static final Integer TYPE_CHARGE = 5;
	public static final Integer TYPE_DONATION = 6;
	public static final Integer TYPE_BONUS = 7;
	public static final Integer TYPE_BONUS_REPASS = 8;
	public static final Integer TYPE_OPERATIONAL = 9;
	public static final Integer TYPE_POLITICAL_DONATION = 10;
	
	public static final Integer STATUS_INITIATED = 0;
	public static final Integer STATUS_WAITING_PAYMENT = 1;
	public static final Integer STATUS_IN_ANALYSIS = 2;
	public static final Integer STATUS_PAID = 3;
	public static final Integer STATUS_AVAILABLE = 4;
	public static final Integer STATUS_IN_DISPUTE = 5;
	public static final Integer STATUS_REFUNDED = 6;
	public static final Integer STATUS_CANCELLED = 7;

	private Date lastEventDate;
	
	private Date date;
	
	private String code;
	
	private String reference;
	
	@XmlElement(name="type")
	private Integer transactionType;
	
	@XmlElement(name="status")
	private Integer transactionStatus;
	
	private PaymentMethod paymentMethod;
	
	private BigDecimal grossAmount;	
	
	private BigDecimal discountAmount;
	
	private BigDecimal feeAmount;
	
	private BigDecimal netAmount;
	
	private BigDecimal extraAmount;
	
	private Integer installmentCount;
	
	@XmlElementWrapper(name="items")
    @XmlElement(name="item")
	private List<Item> items;
	
	private Sender sender;
	
	private Shipping shipping;

	public Date getLastEventDate() {
		return lastEventDate;
	}

	public void setLastEventDate(Date lastEventDate) {
		this.lastEventDate = lastEventDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(Integer transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(BigDecimal extraAmount) {
		this.extraAmount = extraAmount;
	}

	public Integer getInstallmentCount() {
		return installmentCount;
	}

	public void setInstallmentCount(Integer installmentCount) {
		this.installmentCount = installmentCount;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	
}
