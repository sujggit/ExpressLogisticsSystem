package usersl;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;






import java.util.Date;

import dataservice.UserDataService;
import dataserviceimpl.DataFactory;
import enums.ResultMessage;
import po.LogPO;
import userslservice.LogService;
import vo.LogVO;

public class LogManagement implements LogService{
	
	static LogManagement log;
	DataFactory datafactory;
	ArrayList<LogPO> logs;
	ArrayList<LogVO> logVOs;
	
	private LogManagement(DataFactory datafactory){
		this.datafactory=datafactory;
	}
	
	@Override
	public ArrayList<LogVO> logmessage(String office,String time) {
		// TODO Auto-generated method stub
		UserDataService userdata=datafactory.getUserData();
		try {
			logs=userdata.findsLogsPO(office,time);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(LogPO po:logs){
			LogVO vo=new LogVO(po.getTime(), po.getOffice(), po.getUseuId(), po.getLogmessage());
			logVOs.add(vo);
		}

		
		this.log();
		

		
		return logVOs;
	
	}
	
	public void log(){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		
		LogList loglist=LogList.creatLogList(datafactory);
		
		ResultMessage message=loglist.logCreate(time, null, "", "��־��ѯ");
	}
	
	
	
	public static LogManagement creatCheck(DataFactory datafactory){
		if(log==null)
			log = new LogManagement(datafactory);	
		
		 return log;
	}

}