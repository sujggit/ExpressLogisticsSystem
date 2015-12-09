package po;

import java.io.Serializable;

public class ReceiptsPO implements Serializable{
      
	String date;
	double fee;
	String courier;
	String[] ordernumbers;
	String office;
	
	public ReceiptsPO(String date,double fee,String courier,String[] ordernumbers,String office){
		this.date=date;
		this.fee=fee;
		this.courier=courier;
		this.ordernumbers=ordernumbers;
		this.office=office;
		
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getOffice() {
		return office;
	}



	public void setOffice(String office) {
		this.office = office;
	}



	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public String[] getOrdernumbers() {
		return ordernumbers;
	}

	public void setOrdernumbers(String[] ordernumbers) {
		this.ordernumbers = ordernumbers;
	}
	
}