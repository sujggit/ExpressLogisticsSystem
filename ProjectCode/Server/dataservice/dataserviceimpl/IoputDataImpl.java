package dataserviceimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dataservice.IoputDataService;
import po.IoputPO;

public class IoputDataImpl extends UnicastRemoteObject implements IoputDataService {

	private IoputDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IoputPO find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IoputPO[] finds(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IoputPO[] findDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IoputPO[] findDates(String[] date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IoputPO[] findTimes(String[] time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(IoputPO PO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(IoputPO PO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(IoputPO PO) {
		// TODO Auto-generated method stub

	}

	  public static IoputDataImpl create() throws RemoteException{
			if(ioput == null){
				synchronized(IoputDataImpl.class){
			
			if(ioput == null)
			ioput = new IoputDataImpl();
				}
			}
				
			return ioput;
		}
		
	   private volatile static IoputDataImpl ioput;   
	   
}
