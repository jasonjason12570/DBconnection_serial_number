package window;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dbtool.dbconn;


public class window {
	String IP="";
	int IP_seleck=0;
	protected static final Component erro = null;
	dbconn dbconn = new dbconn();
    private JScrollPane scroll,scroll2;
    JFrame jframe = new JFrame();
    boolean check_search = false;//當新增時確認是否有經過新增
	final static JTextArea console_TextArea = new JTextArea(8,12);
	public window(){
		// 建立一個視窗，並將標題設定為「視窗程式」
		// 標題可用jframe.setTitle("視窗程式");代替
		jframe.setTitle("SfwrShipmentRecordTool");
		// 設定圖示
		jframe.setIconImage(jframe.getToolkit().getImage("DIGIWIN.png"));
		// 設定視窗大小(長,寬)
		jframe.setSize(1000, 500);
		//設定開啟的位置和某個物件相同，帶入null則會在畫面中間開啟
		jframe.setLocationRelativeTo(null);
		
		// 關閉選項(右上角的叉叉圖示)按下後的動作
        // EXIT_ON_CLOSE：點選關閉時，關閉程式
        // DISPOSE_ON_CLOSE：點選關閉時，關閉顯示的視窗以及使用的資源，程式仍在背景執行
        // HIDE_ON_CLOSE：點選關閉時，僅隱藏顯示的視窗，程式仍在背景執行
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* 元件 */
       
		
		JLabel inputtype_Label = new JLabel();
		inputtype_Label.setText("輸入類別");
		inputtype_Label.setFont(new Font(null, Font.PLAIN, 14));

		final JRadioButton SO01_RadioButton = new JRadioButton("出貨單號",true);

		final JRadioButton GG001_RadioButton = new JRadioButton("客戶代號");
		
		ButtonGroup inputtype_ButtonGroup = new ButtonGroup();
		inputtype_ButtonGroup.add(SO01_RadioButton);
		inputtype_ButtonGroup.add(GG001_RadioButton);
		
		JLabel SO01_Label = new JLabel();
		SO01_Label.setText("出貨單號");
		SO01_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		final JTextField SO01_TextField = new JTextField(14);
		SO01_TextField.setFont(new Font(null, Font.PLAIN, 14));
		
		final JButton SO01_Button = new JButton("查詢");
		SO01_Button.setFont(new Font(null, Font.PLAIN, 14));
		
		
		

		
		JLabel CA034_Label = new JLabel();
		CA034_Label.setText("建檔人工號");
		CA034_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		final JTextField CA034_TextField = new JTextField(14);
		CA034_TextField.setFont(new Font(null, Font.PLAIN, 14));
		

		
		JLabel date_Label = new JLabel();
		date_Label.setText("出貨日期");
		date_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		//日期模組
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		
		final JTextField datePicker_TextField = new JTextField(14);
		datePicker_TextField.setFont(new Font(null, Font.PLAIN, 14));
		final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setTextEditable(true);
		final String getime ="";
		//final String getime = datePicker.getJFormattedTextField().getText();
		
		
		
		final JComboBox IP_ComboBox = new JComboBox();
		IP_ComboBox.addItem("10.20.99.58--正式主機");
		IP_ComboBox.addItem("10.40.140.188--測式主機");
		IP_ComboBox.setSelectedIndex(0);//預設為正式主機
		IP=IP_ComboBox.getSelectedItem().toString();
			
		JButton date_Button = new JButton("...");
		date_Button.setFont(new Font(null, Font.PLAIN, 14));
		
		JLabel CA039_Label = new JLabel();
		CA039_Label.setText("套數");
		CA039_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		final JTextField CA039_TextField = new JTextField(14);
		CA039_TextField.setFont(new Font(null, Font.PLAIN, 14));
		CA039_TextField.setText("1");
		
		
		JLabel GG001_Label = new JLabel();
		GG001_Label.setText("客戶代號");
		GG001_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		final JTextField GG001_TextField = new JTextField(10);
		GG001_TextField.setFont(new Font(null, Font.PLAIN, 14));
		
		final JButton GG001_Button = new JButton("查詢");
		GG001_Button.setFont(new Font(null, Font.PLAIN, 14));
		GG001_Button.setEnabled(false);
		
		JLabel GG004_Label = new JLabel();
		GG004_Label.setText("客戶名稱");
		GG004_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		final JTextField GG004_TextField = new JTextField(14);
		GG004_TextField.setFont(new Font(null, Font.PLAIN, 14));
		
		JLabel CA040_Label = new JLabel();
		CA040_Label.setText("U數備註");
		CA040_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		
		final JTextArea CA040_TextArea = new JTextArea(3,0);
		CA040_TextArea.setLineWrap(true);
		CA040_TextArea.setFont(new Font(null, Font.PLAIN, 14));
		scroll = new JScrollPane(CA040_TextArea);
		

		JLabel CA007_Label = new JLabel();
		CA007_Label.setText("授權數量");
		CA007_Label.setFont(new Font(null, Font.PLAIN, 14));
		
		final JTextField CA007_TextField = new JTextField();
		CA007_TextField.setFont(new Font(null, Font.PLAIN, 14));
		CA007_TextField.setText("99999");
		CA007_TextField.disable();
		
		
		JButton clean_Button = new JButton("清除");
		clean_Button.setFont(new Font(null, Font.PLAIN, 14));
		
		final JButton submit_Button = new JButton("新增");
		submit_Button.setFont(new Font(null, Font.PLAIN, 14));
		submit_Button.setEnabled(false);
		//console
		
		console_TextArea.setLineWrap(true);
		console_TextArea.setFont(new Font(null, Font.PLAIN, 14));
		//console.disable();
		scroll2 = new JScrollPane(console_TextArea);
		
		
		/* 元件 */
        /* 佈局 */
		final Container mainw = new Container();
		//宣告 GridBagLayout
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		//設定佈局方式為 GridBagLayout
		mainw.setLayout(gridBagLayout);
		
		//宣告網格約束變數
	    GridBagConstraints c = new GridBagConstraints();
	    
		/* 佈局 */
	    /* 部屬元件 */
	    /* 設定網格約束範例
	    c.fill = GridBagConstraints.HORIZONTAL;
	    NONE（預設值）HORIZONTAL （以水平填充其顯示區域）VERTICAL （以垂直填充其顯示區域）（完全填充其顯示區域）
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
		c.gridheight = 1;
	    mainw.add(元件, c);
	    
	    GridBagConstraints mkelement(GridBagConstraints c, String _fill, int _gridx, int _gridy, int _gridwidth, int _gridheight)
	    */

	    mainw.add(IP_ComboBox, mkelement(c,"HORIZONTAL",0,0,1,1));
	    
	    mainw.add(inputtype_Label, mkelement(c,"HORIZONTAL",0,1,1,1));
	    mainw.add(SO01_RadioButton, mkelement(c,"HORIZONTAL",1,1,1,1));
	    mainw.add(GG001_RadioButton, mkelement(c,"HORIZONTAL",2,1,1,1));
	    
	    mainw.add(SO01_Label, mkelement(c,"HORIZONTAL",0,2,1,1));
	    mainw.add(SO01_TextField, mkelement(c,"HORIZONTAL",1,2,1,1));
	    mainw.add(SO01_Button, mkelement(c,"HORIZONTAL",2,2,1,1));
	    
	    mainw.add(CA034_Label, mkelement(c,"HORIZONTAL",0,3,1,1));
	    mainw.add(CA034_TextField, mkelement(c,"HORIZONTAL",1,3,1,1));
	    
	    mainw.add(date_Label, mkelement(c,"HORIZONTAL",0,4,1,1));
	    mainw.add(datePicker, mkelement(c,"HORIZONTAL",1,4,1,1));
	    
	    mainw.add(CA039_Label, mkelement(c,"HORIZONTAL",0,6,1,1));
	    mainw.add(CA039_TextField, mkelement(c,"HORIZONTAL",1,6,1,1));
	    
	    mainw.add(GG001_Label, mkelement(c,"HORIZONTAL",5,1,1,1));
	    mainw.add(GG001_TextField, mkelement(c,"HORIZONTAL",6,1,1,1));
	    mainw.add(GG001_Button, mkelement(c,"HORIZONTAL",7,1,1,1));
	    
	    mainw.add(GG004_Label, mkelement(c,"HORIZONTAL",5,2,1,1));
	    mainw.add(GG004_TextField, mkelement(c,"HORIZONTAL",6,2,2,1));
	    
	    mainw.add(CA040_Label, mkelement(c,"HORIZONTAL",5,3,1,1));
	    mainw.add(scroll, mkelement(c,"HORIZONTAL",6,3,2,3));
	    
	    mainw.add(CA007_Label, mkelement(c,"HORIZONTAL",5,6,1,1));
	    mainw.add(CA007_TextField, mkelement(c,"HORIZONTAL",6,6,2,1));
	    	    
	    mainw.add(clean_Button, mkelement(c,"HORIZONTAL",2,9,1,1));
	    mainw.add(submit_Button, mkelement(c,"HORIZONTAL",4,9,1,1));
	    mainw.add(scroll2, mkelement(c,"HORIZONTAL",0,11,8,12));
	    /* 部屬元件 */
	    
        // 設定視窗顯示，若未設定視窗只會在背景執行
	    jframe.add(mainw);
        jframe.setVisible(true);
		//監聽檢查按鈕事件
        IP_ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IP = IP_ComboBox.getSelectedItem().toString();
				IP_seleck = IP_ComboBox.getSelectedIndex();
				dbconn.dbconn_IP_seleck(IP_seleck);
			}
		});
        
        
        
		SO01_Button.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				check_search=true;//確認資料為經過搜尋的資料
				ArrayList SO01_list = new ArrayList();
				SO01_list=dbconn.db_getSO01(SO01_TextField.getText().toString());
				if(SO01_list.size()!=0){
					GG001_TextField.setText((String) SO01_list.get(0));
					GG004_TextField.setText((String) SO01_list.get(1));
					CA039_TextField.setText((String) SO01_list.get(2));
					SO01_TextField.setEnabled(false);
					SO01_Button.setEnabled(false);
					GG001_TextField.setEnabled(false);
					GG001_Button.setEnabled(false);
					GG004_TextField.setEnabled(false);
					submit_Button.setEnabled(true);
				}else{
					SO01_TextField.setText("");
		        	GG001_TextField.setText("");
		        	GG004_TextField.setText("");
				}
			} 
		});
		GG001_Button.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				//初始化文字
				ArrayList GG001_list = new ArrayList();
				GG001_list=dbconn.db_getGG001(GG001_TextField.getText().toString());
				if(GG001_list.size()!=0){
					GG004_TextField.setText((String) GG001_list.get(0));
					CA039_TextField.setText((String) GG001_list.get(1));
					
					SO01_Button.setEnabled(false);
					GG001_TextField.setEnabled(false);
					GG001_Button.setEnabled(false);
					GG004_TextField.setEnabled(false);
					submit_Button.setEnabled(true);
				
				}else{
					SO01_TextField.setText("");
		        	GG001_TextField.setText("");
		        	GG004_TextField.setText("");
		        	CA039_TextField.setText("");
				}
			} 
		});
		//RadioButton群組
			//選擇出貨單號
		SO01_RadioButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	SO01_TextField.setEnabled(true);
	        	SO01_Button.setEnabled(true);
	        	GG001_Button.setEnabled(false);
	        	submit_Button.setEnabled(false);
	        	//初始化文字
	        	SO01_TextField.setText("");
	        	GG001_TextField.setText("");
	        	GG004_TextField.setText("");
	        }
	    });
			//選擇客戶代號
		GG001_RadioButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	GG001_TextField.setEnabled(true);
	        	SO01_Button.setEnabled(false);
	        	SO01_TextField.setEnabled(true);
	        	GG001_Button.setEnabled(true);
	        	submit_Button.setEnabled(false);
	        	//初始化文字
	        	SO01_TextField.setText("");
	        	GG001_TextField.setText("");
	        	GG004_TextField.setText("");
	        }
	    });
		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gettime=null;
				String change=null;
                gettime = datePicker.getJFormattedTextField().getText();
                change=gettime;
                change=datePicker_TextField.getText();
                gettime=gettime.replaceAll("-", "");
                datePicker_TextField.setText(gettime);
                return ;
            }
	    });
		 
		//新增按鈕事件
		submit_Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String msg="";
				boolean data_ready=true;
				ArrayList insert_data = new ArrayList();
				ArrayList CHECK_ERROR = new ArrayList();//必填值
				
				insert_data.add(SO01_TextField.getText());//出貨單號
				insert_data.add(GG001_TextField.getText());//客戶代號
				insert_data.add(GG004_TextField.getText());//客戶名稱
				insert_data.add(CA034_TextField.getText());//建檔人工號
				insert_data.add(datePicker_TextField.getText());//出貨日期
				
				insert_data.add(CA039_TextField.getText());//套數
				insert_data.add(CA040_TextArea.getText());//U數備註
				insert_data.add(CA007_TextField.getText());//授權數量
				
				ArrayList column_name = new ArrayList();
				column_name.add("出貨單號");//CA036
				column_name.add("客戶代號");//CA001
				column_name.add("客戶名稱");//CA025
				column_name.add("建檔人工號");//CA034
				column_name.add("出貨日期");//CA008 CA033
				column_name.add("套數");//CA039
				column_name.add("U數備註");//CA040
				column_name.add("授權數量");//CA007
				
				int[] max_char={30,10,50,10,8,8,100,5};//限制字數
				for(int num = 0;num<insert_data.size();num++){
					String data = insert_data.get(num).toString();
					String column = column_name.get(num).toString();
					if((data.equals("")||data.equals(null))&&(num!=6&&num!=7)){
						msg=column+"不可空白";
						log(msg);
						CHECK_ERROR.add(msg);
						data_ready=false;
					}else{
						if(data.length()>max_char[num]){
							msg=column+"超過字元上限 : "+max_char[num]+"\n目前字數為 : "+data.length();
							log(msg);
							JOptionPane.showMessageDialog(mainw,msg);
							data_ready=false;
						}
						log(column+"="+data);
					}
				}
				//資料確認完整 開始執行後端
				if(data_ready==true){
					String CA002="";
					int checkCA039=Integer.parseInt(insert_data.get(5).toString());
					boolean continue_insert_sql = false;//確認新增大於二之套數
					boolean check_data=false;//確認資料欄位正確性
					log("資料正確取得:執行後端");
					CA002 = dbconn.insert_sql(insert_data);

					if(checkCA039>=2){
						log("並非第一套，目前已有"+(checkCA039-1)+"套");
						int result=JOptionPane.showConfirmDialog(mainw,
					               "並非第一套，是否確定生產序號?",
					               "確認訊息",
					               JOptionPane.YES_NO_OPTION,
					               JOptionPane.WARNING_MESSAGE);
					    if (result==JOptionPane.YES_OPTION) {//執行新增命令
					    	dbconn.insert_sql(insert_data,CA002);
					    	JOptionPane.showMessageDialog(mainw, "已完成新增");
					    	CA039_TextField.setText(String.valueOf(checkCA039+1));
					    	GG001_Button.setEnabled(true);
					    	SO01_Button.setEnabled(true);
					    	submit_Button.setEnabled(false);
					    }else if(result==JOptionPane.NO_OPTION){
					    	JOptionPane.showMessageDialog(mainw, "已取消新增");
					    	GG001_Button.setEnabled(true);
					    	SO01_Button.setEnabled(true);
					    	submit_Button.setEnabled(false);
					    }
					}else{
						log("首次添加，目前已有"+(checkCA039-1)+"套");
						dbconn.insert_sql(insert_data,CA002);
						JOptionPane.showMessageDialog(mainw, "已完成新增");
						CA039_TextField.setText(String.valueOf(checkCA039+1));
						GG001_Button.setEnabled(true);
				    	SO01_Button.setEnabled(true);
				    	submit_Button.setEnabled(false);
					}
				}else{
					for(int num = 0;num<CHECK_ERROR.size();num++){
					JOptionPane.showMessageDialog(mainw,CHECK_ERROR.get(num));
					}
					log("資料取得不正確");
				}
	        }
		});
		clean_Button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				SO01_TextField.setText("");
	        	GG001_TextField.setText("");
	        	GG004_TextField.setText("");
	        	CA034_TextField.setText("");
	        	CA040_TextArea.setText("");
	        	CA039_TextField.setText("");
	        	SO01_TextField.setEnabled(true);
				SO01_Button.setEnabled(true);
				GG001_TextField.setEnabled(true);
				GG001_Button.setEnabled(true);
				GG004_TextField.setEnabled(true);
				submit_Button.setEnabled(false);
				SO01_RadioButton.doClick();
				
				}
			});
	}
	public static String getDateTime(){
		SimpleDateFormat sdFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		
		return strDate;
		}
	public static String log(String _addconsole){
		String console="";
		if(console_TextArea.getText().equals("")){
			console=getDateTime()+" "+_addconsole;
		}else{
			console=console_TextArea.getText()+"\n"+getDateTime()+" "+_addconsole;
		}
		
		System.out.println(_addconsole);
		console_TextArea.setText(console);
		
		return null;
	}
	
	GridBagConstraints mkelement(GridBagConstraints c, String _fill, int _gridx, int _gridy, int _gridwidth, int _gridheight){
		if(_fill.equals("NONE")){
			c.fill = GridBagConstraints.NONE;
		}else if(_fill.equals("HORIZONTAL")){
			c.fill = GridBagConstraints.HORIZONTAL;
		}else if(_fill.equals("VERTICAL")){
			c.fill = GridBagConstraints.VERTICAL;
		}else if(_fill.equals("BOTH")){
			c.fill = GridBagConstraints.BOTH;
		}
		
	    c.gridx = _gridx;
	    c.gridy = _gridy;
	    c.gridwidth = _gridwidth;
	    c.gridheight = _gridheight;
	    
	    return c;
	}
}
