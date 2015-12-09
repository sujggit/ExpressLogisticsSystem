
package dataserviceimpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataservice.TransportDataService;
import enums.Condition;
import enums.DocumentCondition;
import enums.Position;
import enums.ResultMessage;
import enums.Traffic;
import enums.TransportType;
import link.Helper;
import po.TransportPO;

public class TransportDataImpl extends UnicastRemoteObject implements TransportDataService,Serializable{

	protected TransportDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public TransportPO find(String id) {
		// TODO Auto-generated method stub
		String sql = "select* from transportpo where id='"+id+"';";
		ArrayList<String> member = null;
		ArrayList<String> order = null;
		ArrayList<Condition> condition = null;
		ResultSet result = null;
		TransportPO po =null;
		try{
			result = Helper.find(sql);
			if(result.next()){

				//ObjectInputStream oips = new ObjectInputStream(result.getBinaryStream("member"));  
				member = (ArrayList<String>)IOObject.getArray(result.getBytes("member"));
				//oips =  new ObjectInputStream(result.getBinaryStream("order"));
				order =  (ArrayList<String>)IOObject.getArray(result.getBytes("order"));
				//oips =  new ObjectInputStream(result.getBinaryStrem("condition"));
				condition = (ArrayList<Condition>)IOObject.getArray(result.getBytes("condition"));
				po = new TransportPO(TransportType.valueOf(result.getString("sign")),result.getString("id"),
						result.getString("transportID"),Position.valueOf(result.getString("departure")),
						Position.valueOf(result.getString("destination")),result.getString("time"),result.getString("trafficID"),
						Traffic.valueOf(result.getString("traffic")),
						result.getDouble("fare"),member,order,condition,
						DocumentCondition.valueOf(result.getString("DocumentCondition")),result.getString("nameOfWriter"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return po;
	}



	@Override
	public ResultMessage insert(TransportPO po){
		String sql = "insert into transportpo values('"+po.getSign()+"','"+po.getID()+"','"+po.getTransportID()+"','"+po.getDeparture()+"','"
				+po.getDestination()+"','"+po.getTime()+"','"+po.getTrafficID()+"','"+po.getTrafficType()+"',"+po.getfare()+",?,?,?,'"
				+po.getDocumentCondition()+"','"+po.getWriter() +"');";		// TODO Auto-generated method stub
		try {
			Helper.pStatement = Helper.conn.prepareStatement(sql);
			Helper.pStatement.setObject(1, IOObject.toByteArray(po.getMember()));
			Helper.pStatement.setObject(2, IOObject.toByteArray(po.getOrder()));
			Helper.pStatement.setObject(3, IOObject.toByteArray(po.getCondition()));
			Helper.pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAIL;
		}

		return ResultMessage.SUCCESS;   
	}



	@Override
	public ResultMessage delete(TransportPO po) {
		String sql = "delete from transportpo where id='"+po.getID()+"';";
		// TODO Auto-generated method stub
		return Helper.delete(sql);
	}

	@Override
	public ResultMessage update(TransportPO po) {
		ResultMessage result = delete(po);
		if(result==ResultMessage.FAIL)
			return ResultMessage.FAIL;
		return insert(po);
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
	public static TransportDataImpl create() throws RemoteException {
		if(transport == null){
			synchronized(TransportDataImpl.class){

				if(transport == null)
					transport = new TransportDataImpl();
			}
		}

		return transport;
	}

	private volatile static TransportDataImpl transport;

	@Override
	public ArrayList<TransportPO> finds(TransportType sign) {
		// TODO Auto-generated method stub
		ArrayList<TransportPO>pos = new ArrayList<TransportPO>();
		String sql = "select* from transportpo where sign='"+sign+"';";
		ArrayList<String> member = null;
		ArrayList<String> order = null;
		ArrayList<Condition> condition = null;
		ResultSet result = null;
		TransportPO po =null;
		try{
			result = Helper.find(sql);
			while(result.next()){

				//ObjectInputStream oips = new ObjectInputStream(result.getBinaryStream("member"));  
				member = (ArrayList<String>)IOObject.getArray(result.getBytes("member"));
				//oips =  new ObjectInputStream(result.getBinaryStream("order"));
				order =  (ArrayList<String>)IOObject.getArray(result.getBytes("order"));
				//oips =  new ObjectInputStream(result.getBinaryStream("condition"));
				condition = (ArrayList<Condition>)IOObject.getArray(result.getBytes("condition"));
				po = new TransportPO(TransportType.valueOf(result.getString("sign")),result.getString("id"),
						result.getString("transportID"),Position.valueOf(result.getString("departure")),
						Position.valueOf(result.getString("destination")),result.getString("time"),result.getString("trafficID"),
						Traffic.valueOf(result.getString("traffic")),
						result.getDouble("fare"),member,order,condition,
						DocumentCondition.valueOf(result.getString("DocumentCondition")),result.getString("nameOfWriter"));
				pos.add(po);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pos;
	}
	@Override
	public ArrayList<TransportPO> findWithdCondition(String nameOfWriter, DocumentCondition dCondition) {
		// TODO Auto-generated method stub
		ArrayList<TransportPO>pos = new ArrayList<TransportPO>();
		String sql = "select* from transportpo where nameOfWriter='"+nameOfWriter+"' and documentCondition='"+dCondition+"';";
		ArrayList<String> member = null;
		ArrayList<String> order = null;
		ArrayList<Condition> condition = null;
		ResultSet result = null;
		TransportPO po =null;
		try{
			result = Helper.find(sql);
			while(result.next()){

				//ObjectInputStream oips = new ObjectInputStream(result.getBinaryStream("member"));  
				member = (ArrayList<String>)IOObject.getArray(result.getBytes("member"));
				//oips =  new ObjectInputStream(result.getBinaryStream("order"));
				order =  (ArrayList<String>)IOObject.getArray(result.getBytes("order"));
				//oips =  new ObjectInputStream(result.getBinaryStream("condition"));
				condition = (ArrayList<Condition>)IOObject.getArray(result.getBytes("condition"));
				po = new TransportPO(TransportType.valueOf(result.getString("sign")),result.getString("id"),
						result.getString("transportID"),Position.valueOf(result.getString("departure")),
						Position.valueOf(result.getString("destination")),result.getString("time"),result.getString("trafficID"),
						Traffic.valueOf(result.getString("traffic")),
						result.getDouble("fare"),member,order,condition,
						DocumentCondition.valueOf(result.getString("DocumentCondition")),result.getString("nameOfWriter"));
			
				pos.add(po);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pos;
	}



}
