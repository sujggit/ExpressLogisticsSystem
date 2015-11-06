package manangerUI;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.BaseUI;

public class SalaryStrategyUI extends JPanel {
	
	public SalaryStrategyUI(){
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		newSalary = new JButton("�µ�нˮ����");		
		check=new JButton("нˮ���Բ鿴");
		name=new JLabel("��������");
		time=new JLabel("�ƶ�ʱ��");
		state=new JLabel("ʹ��״̬");
	    this.setLayout(new BorderLayout());
		
		
		GridBagLayout gb=new GridBagLayout(); 
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets=new Insets(25,0,25,0);
		JPanel bpanel = new JPanel();
		bpanel.setLayout(gb);
		BaseUI.myAdd(bpanel, newSalary, constraints, 0, 0, 1, 1);
		this.add(bpanel, BorderLayout.WEST);
		BaseUI.myAdd(bpanel, check, constraints, 0, 1, 1, 1);
		
		
		JPanel lpanel=new JPanel();
		lpanel.setLayout(gb);
		constraints.insets=new Insets(0,0,0,100);
		
		BaseUI.myAdd(lpanel,name,constraints,0,0,1,1);
		BaseUI.myAdd(lpanel,time,constraints,1,0,1,1);
		BaseUI.myAdd(lpanel,state,constraints,2,0,1,1);

	
		
		String nation[] ={"1","2","3","4"};
		list=new JList(nation);
		this.add(new JScrollPane(list));
		
		
		this.add(lpanel,BorderLayout.NORTH);
		this.add(list,BorderLayout.CENTER);
		this.setVisible(true);
		
	}

	
	JButton newSalary;
	JButton check;
	JList list;
	JLabel name;
	JLabel time;
	JLabel state;
}