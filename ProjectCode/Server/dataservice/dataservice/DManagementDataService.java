package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.DriverPO;

public interface DManagementDataService extends Remote{
	
	public DriverPO find(String id) throws RemoteException;
	public ArrayList<DriverPO> findAll() throws RemoteException;
	public void insert(DriverPO po) throws RemoteException;
	public void delect(DriverPO po) throws RemoteException;
	public void delect(String id) throws RemoteException;
	public void update(DriverPO po) throws RemoteException;
	public void init() throws RemoteException;
	public void finish() throws RemoteException;

}
