package financeui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;




import javax.swing.text.MaskFormatter;

import dataserviceimpl.DataFactory;
import enums.ResultMessage;
import pamanagementsl.AManagementController;
import financesl.AccountManagementController;
import free.BaseUI;
import free.FreePagePane;
import free.FreeReportPage;
import free.FreeToolbarButton;
import free.FreeToolbarRoverButton;
import free.FreeUtil;
import twaver.TWaverUtil;
import vo.AccountVO;

public class AccountManagementPanel{
	private static JPanel addAccountPanel,deleteAccountPanel,fixAccountPanel,seekAccountPanel;
	public static JTabbedPane tab;
	private static AccountManagementController amc;
	private static AccountVO deletevo,fixvo;
	
	
	

	
	public static FreeReportPage  createAccountManagementPage(JTabbedPane tab){

	    AccountManagementPanel.tab=tab;
	    
//		JButton addUser,deleteUser,fixUser,seekUser;
//		addUser=new JButton("增加用户");
//		deleteUser=new JButton("删除用户");
//		fixUser=new JButton("修改用户");
//		seekUser=new JButton("寻找用户");
//		
//
//		
//		
//		userManagementPanel.add(addUser);
//		userManagementPanel.add(deleteUser);
//		userManagementPanel.add(fixUser);
//		userManagementPanel.add(seekUser);
		
		return createReportPage();
	}
	
	private static void createAddAccountPanel(){
		addAccountPanel=new JPanel();
		JLabel name=new JLabel("账户名称:");
		JLabel balance=new JLabel("账户余额");

		JTextField namefield=new JTextField(10);
		JTextField balancefield=new JTextField(20);


		
		
        
		GridBagLayout gb=new GridBagLayout(); 
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets=new Insets(25,0,25,0);
		JPanel bpanel = new JPanel();
		bpanel.setLayout(gb);
		
		BaseUI.myAdd(bpanel,name,constraints,0,0,1,1);
		BaseUI.myAdd(bpanel,balance,constraints,0,1,1,1);
		BaseUI.myAdd(bpanel,namefield,constraints,1,0,1,1);
		BaseUI.myAdd(bpanel,balancefield,constraints,1,1,1,1);

		
		JButton submit=new JButton("提交");
		BaseUI.myAdd(bpanel,submit,constraints,0,2,2,1);
		submit.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent arg0) 
		    {
		    	if(!namefield.getText().equals("")&&!balancefield.getText().equals("")){
		    		amc=new AccountManagementController();

		    		if(!amc.findAccount(namefield.getText()).equals("")){
	         	    	  JOptionPane.showMessageDialog(null, "新增失败,已存在同名账户"); 
		    		}else{
			    		try {
			    			ResultMessage result=null;
			    			double _balance=Double.parseDouble(balancefield.getText());
			    			result=amc.addAccount(namefield.getText(), _balance);
						       if(result==ResultMessage.SUCCESS){
				         	    	  JOptionPane.showMessageDialog(null, "新增成功"); 
				         	    	  tab.remove(FreeUtil.getPagePane(addAccountPanel));
							       }else{
					         	    	  JOptionPane.showMessageDialog(null, "新增失败"); 
							       }
			    		} catch (Exception e) {
			    			// TODO Auto-generated catch block
			    			e.printStackTrace();
			    		}
		    		}
		    		

		    	}
		    }
		});    
		
		
		addAccountPanel.add(bpanel);
	}
	
	
	
	private static void createDeleteAccountPanel(){
		deleteAccountPanel=new JPanel();
		GridBagLayout gb=new GridBagLayout(); 
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets=new Insets(25,0,25,0);
		JPanel bpanel = new JPanel();
		bpanel.setLayout(gb);
		
		
		JLabel account=new JLabel("账户");
		JTextField accountfield=new JTextField(10);
		BaseUI.myAdd(bpanel,account,constraints,0,0,1,1);
		BaseUI.myAdd(bpanel,accountfield,constraints,1,0,1,1);
		
		JButton submit=new JButton("查询删除账户信息");
		BaseUI.myAdd(bpanel,submit,constraints,0,5,2,1);
		submit.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent arg0) 
		    {   
	            if(submit.getText().equals("查询删除用户信息")){
	              	if(accountfield.getText().equals("")){
	         	    	  JOptionPane.showMessageDialog(null, "请输入账户名"); 
	              	}else{
	              		deletevo=amc.findAccount(accountfield.getText());
			    	    submit.setText("确认删除");
			    	    bpanel.remove(account);
			    	    bpanel.remove(accountfield);
					    JLabel name=new JLabel("账户名称:");
					    JLabel balance=new JLabel("账户余额");

					    JLabel namefield=new JLabel(deletevo.getName());
					    JLabel balancefield=new JLabel(deletevo.getBalance()+"");
					
					    BaseUI.myAdd(bpanel,name,constraints,0,0,1,1);
					    BaseUI.myAdd(bpanel,balance,constraints,0,1,1,1);
					    BaseUI.myAdd(bpanel,namefield,constraints,1,0,1,1);
					    BaseUI.myAdd(bpanel,balancefield,constraints,1,1,1,1);
	              	}
	            }else if(submit.getText().equals("确认删除")){
	            	  ResultMessage result;
	            	  result=amc.deleteAccount(deletevo);
				       if(result==ResultMessage.SUCCESS){
		         	    	  JOptionPane.showMessageDialog(null, "删除成功"); 
		         	    	  tab.remove(FreeUtil.getPagePane(deleteAccountPanel));
					       }else{
			         	      JOptionPane.showMessageDialog(null, "删除失败"); 
					       }
	            }
				
		    }
		});
		
		deleteAccountPanel.add(bpanel);
	}
	
	private static void createFixAccountPanel(){
		fixAccountPanel=new JPanel();
		
		GridBagLayout gb=new GridBagLayout(); 
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets=new Insets(25,0,25,0);
		JPanel bpanel = new JPanel();
		bpanel.setLayout(gb);
		
		JLabel account=new JLabel("账户");
		JTextField accountfield=new JTextField(10);
		BaseUI.myAdd(bpanel,account,constraints,0,0,1,1);
		BaseUI.myAdd(bpanel,accountfield,constraints,1,0,1,1);
		
		JButton submit=new JButton("查询修改账户信息");
		BaseUI.myAdd(bpanel,submit,constraints,0,5,2,1);
		
		JLabel name=new JLabel("账户名称:");
		JTextField namefield=new JTextField(10);
		
		submit.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent arg0) 
		    {   
	            if(submit.getText().equals("查询删除用户信息")){
	              	if(accountfield.getText().equals("")){
	        	    	  JOptionPane.showMessageDialog(null, "请输入账户名"); 
	              	}else{
	              	fixvo=amc.findAccount(accountfield.getText());

					JLabel balance=new JLabel("账户余额");

					namefield.setText(fixvo.getName());
					JLabel balancefield=new JLabel();
					balancefield.setText(fixvo.getBalance()+"");
					
					BaseUI.myAdd(bpanel,name,constraints,0,0,1,1);
					BaseUI.myAdd(bpanel,balance,constraints,0,1,1,1);
					BaseUI.myAdd(bpanel,namefield,constraints,1,0,1,1);
					BaseUI.myAdd(bpanel,balancefield,constraints,1,1,1,1);
					
					submit.setText("确认修改");
	              	}
	            }else if(submit.getText().equals("确认修改")){
	            	
		    		if(!amc.findAccount(namefield.getText()).equals("")){
	         	    	  JOptionPane.showMessageDialog(null, "修改失败,已存在同名账户"); 
		    		}else{
	            	   
	            	   ResultMessage result=null;
	            	   result=amc.fixAccount(fixvo, namefield.getText());
				       if(result==ResultMessage.SUCCESS){
		         	    	  JOptionPane.showMessageDialog(null, "修改成功"); 
		         	    	  tab.remove(FreeUtil.getPagePane(fixAccountPanel));
					       }else{
			         	      JOptionPane.showMessageDialog(null, "修改失败"); 
					       }
				       
		    		} 
	            }

		    }
		    
		});
		
		fixAccountPanel.add(bpanel);

		
	}
	
	private static void createSeekAccountPanel(){
		seekAccountPanel=new JPanel();
		GridBagLayout gb=new GridBagLayout(); 
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets=new Insets(25,0,25,0);
		JPanel bpanel = new JPanel();
		bpanel.setLayout(gb);
		
		
		JLabel account=new JLabel("账户");
		JTextField accountfield=new JTextField(10);
		BaseUI.myAdd(bpanel,account,constraints,0,0,1,1);
		BaseUI.myAdd(bpanel,accountfield,constraints,1,0,1,1);
		
		JButton submit=new JButton("查询账户信息");
		BaseUI.myAdd(bpanel,submit,constraints,0,5,2,1);
		submit.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent arg0) 
		    {   
	            if(submit.getText().equals("查询删除用户信息")){
	              	if(accountfield.getText().equals("")){
	        	    	  JOptionPane.showMessageDialog(null, "请输入账户名"); 
	              	}else{
	              		
	    		    	submit.setText("确认");
	    		    	bpanel.remove(account);
	    		    	bpanel.remove(accountfield);
	    		    	
	    		    	AccountVO vo=amc.findAccount(accountfield.getName());
	    				JLabel name=new JLabel("账户名称:");
	    				JLabel balance=new JLabel("账户余额");

	    				JLabel namefield=new JLabel(vo.getName());
	    				JLabel balancefield=new JLabel(vo.getBalance()+"");
	    				
	    				BaseUI.myAdd(bpanel,name,constraints,0,0,1,1);
	    				BaseUI.myAdd(bpanel,balance,constraints,0,1,1,1);
	    				BaseUI.myAdd(bpanel,namefield,constraints,1,0,1,1);
	    				BaseUI.myAdd(bpanel,balancefield,constraints,1,1,1,1);  
	              	}
	            }else{
	            	tab.remove(FreeUtil.getPagePane(seekAccountPanel));
	            }
          
		    }
		});
		
		seekAccountPanel.add(bpanel);
	}
	
    private static FreeReportPage createReportPage() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("账户名称");
        model.addColumn("账户余额");
        
   
//        ArrayList<AccountVO> list=new ArrayList<AccountVO>();
//        amc=new AccountManagementController(new DataFactory());
//        list=amc.getAllAccount();
//        for(int i=0;i<list.size();i++){
//            Vector row = new Vector();
//            row.add(list.get(i).getName());
//            row.add(list.get(i).getBalance()+"");
//        }
        
        
        for (int i = 0; i < 100; i++) {
            Vector row = new Vector();
            row.add("工资一");
            row.add("100000");

            model.addRow(row);
        }

        FreeReportPage page = new FreeReportPage();
        page.getTable().setModel(model);
        page.setDescription("All Work Order Items by Part Number. Created " + new Date().toString());
        setupPageToolbar(page);

        return page;
    }

  public static void setupPageToolbar(FreePagePane page) {
	  FreeToolbarButton addAccount,deleteAccount,fixAccount,seekAccount;
	  addAccount=createButton("/free/test/add.png", "增加账户", true);
      deleteAccount=createButton("/free/test/update.png", "删除账户", true);
      fixAccount=createButton("/free/test/refresh.png", "修改账户", true);
      seekAccount=createButton("/free/test/print.png", "查找账户", true);
      page.getRightToolBar().add(addAccount);
      page.getRightToolBar().add(deleteAccount);
      page.getRightToolBar().add(fixAccount);
      page.getRightToolBar().add(seekAccount);
      
		addAccount.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent arg0) 
		    {   
		    	String title=addAccount.getToolTipText();                
                try{
                 FreePagePane pagePane = FreeUtil.getPagePane(addAccountPanel);
                 tab.setSelectedComponent(pagePane);
                }catch(Exception ex){
                    createAddAccountPanel();
             	    tab.addTab(title, FinanceUI.createPage(addAccountPanel));
                    FreePagePane pagePane = FreeUtil.getPagePane(addAccountPanel);
                    tab.setSelectedComponent(pagePane);
                }

             
		    }
		});
		
		
		deleteAccount.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent arg0) 
		    {   
		    	String title=deleteAccount.getToolTipText();                
                try{
                 FreePagePane pagePane = FreeUtil.getPagePane(deleteAccountPanel);
                 tab.setSelectedComponent(pagePane);
                }catch(Exception ex){
                    createDeleteAccountPanel();
             	    tab.addTab(title, FinanceUI.createPage(deleteAccountPanel));
                    FreePagePane pagePane = FreeUtil.getPagePane(deleteAccountPanel);
                    tab.setSelectedComponent(pagePane);
                }

             
		    }
		});
		
		
		fixAccount.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent arg0) 
		    {   
		    	String title=fixAccount.getToolTipText();                
                try{
                 FreePagePane pagePane = FreeUtil.getPagePane(fixAccountPanel);
                 tab.setSelectedComponent(pagePane);
                }catch(Exception ex){
                    createFixAccountPanel();
             	    tab.addTab(title, FinanceUI.createPage(fixAccountPanel));
                    FreePagePane pagePane = FreeUtil.getPagePane(fixAccountPanel);
                    tab.setSelectedComponent(pagePane);
                }

             
		    }
		});
		
		seekAccount.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent arg0) 
		    {   
		    	String title=seekAccount.getToolTipText();                
                try{
                 FreePagePane pagePane = FreeUtil.getPagePane(seekAccountPanel);
                 tab.setSelectedComponent(pagePane);
                }catch(Exception ex){
                    createSeekAccountPanel();
             	    tab.addTab(title, FinanceUI.createPage(seekAccountPanel));
                    FreePagePane pagePane = FreeUtil.getPagePane(seekAccountPanel);
                    tab.setSelectedComponent(pagePane);
                }

             
		    }
		});
      
}
  public static FreeToolbarButton createButton(String icon, String tooltip, boolean rover) {
      FreeToolbarButton button = null;
      if (rover) {
          button = new FreeToolbarRoverButton();
      } else {
          button = new FreeToolbarButton();
      }
      button.setIcon(TWaverUtil.getIcon(icon));
      button.setToolTipText(tooltip);
      
      return button;
  }
}
