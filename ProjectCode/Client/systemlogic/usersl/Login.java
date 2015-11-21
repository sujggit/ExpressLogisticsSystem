package usersl;



import dataservice.UserDataService;
import dataserviceimpl.DataFactory;
import po.UserPO;
import enums.LoginResult;
import userslservice.LoginService;

public class Login implements LoginService{
	static Login login;
	DataFactory datafactory;
	UserPO user;
	
	
	private Login(DataFactory datafactory){
		this.datafactory=datafactory;
	}

	@Override
	public LoginResult login(String[] accountandcode) {
		// TODO Auto-generated method stub
		UserDataService data=datafactory.getUserData();
		user=data.findUserPO(accountandcode[0]);
		LoginResult result=null;
		
		if(user==null){
			return LoginResult.WrongAccount;
		}else if(!accountandcode[1].equals(user.getCode())){
			return LoginResult.WrongCode;
		}
		
		
		switch(user.getWork()){
		   case Courier:
			   return LoginResult.Courier;
		   case Finance:
			   return LoginResult.Finance;
		   case Manager:
			   return LoginResult.Manager;
		   case Admin :
			   return LoginResult.Admin;
		   case Stock:
			   return LoginResult.Stock;
		   case TransOffice:
			   return LoginResult.TransOffice;
			   
		}
		
		
		
		return result;
	}
	
	static Login creatLogin(DataFactory datafactory){
		if(login==null)
			login = new Login(datafactory);	
		
		 return login;
	}

}