package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import enums.DocumentCondition;
import enums.ResultMessage;
import po.*;

public interface SendDataService extends Remote{
    public ResultMessage insertOrderPO(OrderPO order) throws RemoteException;
    
    public ResultMessage insertReceiptsPO(ReceiptsPO receipts) throws RemoteException;
    
    public ArrayList<ReceiptsPO> findReceipts(String date,String office) throws RemoteException;
    
    public ArrayList<OrderPO> findForGathering(String courier,String date) throws RemoteException;
    
    public OrderPO findLatest() throws RemoteException;
    
    public ArrayList<OrderPO> findReceived() throws RemoteException;

	   public ArrayList<OrderPO> findWithdCondition(String nameOfCourier, DocumentCondition dCondition)
			throws RemoteException;
}