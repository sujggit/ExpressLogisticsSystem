package transportsl;

import po.TransportPO;
import dataservice.DataFactoryService;
import dataservice.TransportDataService;
import enums.Condition;
import enums.Position;
import enums.ResultMessage;
import enums.Traffic;
import enums.TransportType;
import vo.TransportVO;

public class Transport {
	DataFactoryService datafactory;
	TransportDataService transportData;
	ConstantInfo constantinfo;

	public Transport(DataFactoryService datafactory, ConstantInfo constantinfo) {
		this.constantinfo = constantinfo;
		this.datafactory = datafactory;
		this.transportData = datafactory.getTransportDate();
	}

	public TransportVO getTransport(String Transportid) throws Exception {
		TransportPO po = transportData.find(Transportid);
		TransportVO vo = new TransportVO(po.getSign(), po.getTransportID(),
				po.getID(), po.getDeparture(), po.getDestination(),
				po.getTime(), po.getTrafficID(), po.getTrafficType(),
				po.getfare(), po.getMember(), po.getOrder(), po.getCondition(),
				po.getDocumentCondition(), po.getWriter());
		return vo;
	}

	public void choose(TransportType sign, TransportVO transportvo) {
		transportvo.setSign(sign);
	}

	public void addMember(String id, TransportVO transportvo) {
		transportvo.getMember().add(id);
	}

	public void addExpress(String orderNumber, TransportVO transportvo) {
		transportvo.getOrder().add(orderNumber);
	}

	public void addCondition(String orderNumber, Condition conditon,
			TransportVO transportvo) {
		transportvo.getCondition().add(conditon);
	}

	public void addMessage(Position departure, Position destination,
			String time, TransportVO transportvo) {
		transportvo.setDeparture(departure);
		transportvo.setDestination(destination);
		transportvo.setTime(time);
	}

	public void addTraffic(String id, Traffic trafficType,
			TransportVO transportvo) {
		transportvo.setTrafficID(id);
		transportvo.setTrafficType(trafficType);
	}

	public void addFare(TransportVO transportvo) {
		double fare = constantinfo.getFare(transportvo.getDeparture(),
				transportvo.getDestination(), transportvo.getTrafficType());
		transportvo.setFare(fare);
	}

	public ResultMessage saveTransport(TransportVO transportvo)
			throws Exception {
		TransportPO po = new TransportPO(transportvo.getSign(),
				transportvo.getID(), transportvo.getTransportID(),
				transportvo.getDeparture(), transportvo.getDestination(),
				transportvo.getTime(), transportvo.getTrafficID(),
				transportvo.getTrafficType(), transportvo.getfare(),
				transportvo.getMember(), transportvo.getOrder(),
				transportvo.getCondition(), transportvo.getDocumentCondition(),
				transportvo.getWriter());
		return transportData.insert(po);
	}

	public void printTransport(String id) {

	}

	public void endTransport() throws Exception {
		transportData.finish();
	}

}
