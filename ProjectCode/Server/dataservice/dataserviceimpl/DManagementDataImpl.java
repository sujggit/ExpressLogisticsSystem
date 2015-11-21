package dataserviceimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataservice.DManagementDataService;
import po.DriverPO;
import po.StaffPO;

public class DManagementDataImpl extends UnicastRemoteObject implements DManagementDataService {

	private DManagementDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public DriverPO find(String id) {
		// TODO Auto-generated method stub
		DriverPO  driver1 = new DriverPO();
		driver1.setName("oldYellow");
		return driver1;
	}

	@Override
	public ArrayList<DriverPO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DriverPO po) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delect(DriverPO po) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delect(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DriverPO po) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	public static DManagementDataImpl create() throws RemoteException{
		if(dm == null){
			synchronized(DManagementDataImpl.class){
		
		if(dm == null)
		dm = new DManagementDataImpl();
			}
		}
			
		return dm;
	}
	
   private volatile static DManagementDataImpl dm;
}
