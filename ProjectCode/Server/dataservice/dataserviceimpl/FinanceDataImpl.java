package dataserviceimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;

import dataservice.FinanceDataService;
import enums.PaymentType;
import enums.ResultMessage;
import link.Helper;
import po.AccountPO;
import po.CompanyAccountPO;
import po.PaymentPO;
import po.ReceiptsPO;

public class FinanceDataImpl extends UnicastRemoteObject implements FinanceDataService {

	private FinanceDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public ReceiptsPO findReceiptsPO(String id) {
//		// TODO Auto-generated method stub
//		ResultSet result = null; 
//		String sql1 = "set sql_safe_update=0;";
//		String sql2 = "select*from receiptspo where data = '"+id+"';";
//		ReceiptsPO po = null;
//		try{
//			Helper.insert(sql1);
//			result = Helper.find(sql2);
//			if(result.next()){
//				String[]orderNumbers = result.getString("orderNumbers").split(" ");
//			po = new ReceiptsPO(result.getString("data"),result.getDouble("fee"),result.getString("courier"),orderNumbers);
//			
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//		
//		return null;
//	}



	@Override
	public ResultMessage insertPaymentPO(PaymentPO payment) {
		
		// TODO Auto-generated method stub
		String sql = "insert into paymentpo values('"+payment.getReceiver()+"','"+payment.getType()+"',"+payment.getNumberOfPayment()+");";
		return Helper.insert(sql);
	}

	@Override
	public AccountPO findAccountPO(String id) {
		// TODO Auto-generated method stub
		AccountPO po = null; 
		ResultSet result = null;
		String sql = "select*from Accountpo where name='"+id+"';";
		try{
			result = Helper.find(sql);
			if(result.next())
				po = new AccountPO(result.getString("name"),result.getDouble("balance"));
		}catch (Exception e){
			e.printStackTrace();
		}
		return po;
	}


	@Override
	public ResultMessage insertAccountPO(AccountPO account) {
		// TODO Auto-generated method stub
		String sql = "insert into accountpo value('"+account.getName()+"',"+account.getBalance()+");";
		return Helper.insert(sql);
	}

	@Override
	public ResultMessage deleteAccountPO(AccountPO account) {
		// TODO Auto-generated method stub
		String sql1 = "set sql_safe_updates=0;";
		String sql2 = "delete from accountpo where name='"+account.getName()+"';";
		String sql3 = "set sql_safe_updates=1;";
		Helper.insert(sql1);
		Helper.delete(sql2);
		Helper.insert(sql3);
		return null;
	}

	@Override
	public ResultMessage updateAccountPO(AccountPO account) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ResultMessage result = deleteAccountPO(account);
	    if(result==ResultMessage.FAIL)
	    	return result;
	    return insertAccountPO(account);
	}

	@Override
	public CompanyAccountPO findCompanyAccountPO(String id) {
		// TODO Auto-generated method stub
		CompanyAccountPO po = null; 
		ResultSet result = null;
		String sql = "select*from CompanyAccountpo where agency='"+id+"';";
		try{
			result = Helper.find(sql);
			if(result.next())
				po = new CompanyAccountPO(result.getString("customer"),result.getString("agency"),result.getString("people"),result.getString("stock"),result.getString("bankaccount"));
		}catch (Exception e){
			e.printStackTrace();
		}
		return po;
	}

	@Override
	public ResultMessage insertCompanyAccountPO(CompanyAccountPO companyaccount) {
		// TODO Auto-generated method stub
		String sql = "insert into accountpo value('"+companyaccount.getCustomer()+"','"+companyaccount.getAgency()+"','"+companyaccount.getPeople()+"','"+companyaccount.getStock()+"','"+companyaccount.getBankaccount()+"');";
		return Helper.insert(sql);
	}

	public static FinanceDataImpl creat() throws RemoteException {
		if(finance == null){
			synchronized(FinanceDataImpl.class){
		
		if(finance == null)
		finance = new FinanceDataImpl();
			}
		}
			
		return finance;
	}
	
   private volatile static FinanceDataImpl finance;

@Override
public ArrayList<AccountPO> seekAccount(String name) {
	// TODO Auto-generated method stub
	ArrayList<AccountPO>pos = new ArrayList<AccountPO>();
	AccountPO po = null; 
	ResultSet result = null;
	String sql = "select*from Accountpo where name like'%"+name+"%';";
	try{
		result = Helper.find(sql);
		while(result.next()){
			po = new AccountPO(result.getString("name"),result.getDouble("balance"));
			pos.add(po);
		}
		
	}catch (Exception e){
		e.printStackTrace();
	}
	return pos;
}

@Override
public ArrayList<PaymentPO> findsPaymentPO(String workplacenumber) throws RemoteException {
	// TODO Auto-generated method stub
	ArrayList<PaymentPO>pays = new ArrayList<PaymentPO>();
	PaymentPO po = null;
	ResultSet result = null;
	String sql = "select *from paymentpo;";
	try{
		result = Helper.find(sql);
		while(result.next()){
			po = new PaymentPO(result.getString("receiver"),PaymentType.valueOf(result.getString("paymentype")),result.getDouble("numberofpayment"));
		   pays.add(po);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return pays;
}

}
