package dbtool;
import window.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class dbconn {
	//正式主機
	private String formal_user="dscpwc";
	private String formal_password="dscpwc";
	private String formal_url="jdbc:sqlserver://10.20.99.58:1433;database=MIS_DOM";
	//測式主機
	private String test_user="sa";
	private String test_password="P@ssword";
	private String test_url="jdbc:sqlserver://10.40.140.188:1433;database=MIS_DOM";
	static Connection conn;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public dbconn(){
		String url=formal_url;
		String user=formal_user;
		String password=formal_password;
		
		window.log("目前選擇的連線是正式主機 :10.20.99.58");
		
		try{
			Class.forName(driver).newInstance();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (InstantiationException e) {
			e.printStackTrace();
		}
		
		try{
			if(conn==null||conn.isClosed()){
				conn = DriverManager.getConnection(url, user, password);
			}else{
				conn.close();
				conn = DriverManager.getConnection(url, user, password);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
			window.log("資料庫連線失敗!!");
		}
	}
	
	public void dbconn_IP_seleck(int IP_seleck){
		/*連線確認是否有已連線*/
		
		String url="";
		String user="";
		String password="";
		if(IP_seleck==0){
			url = formal_url;
			user = formal_user;
			password = formal_password;
			window.log("目前選擇的連線是正式主機 :10.20.99.58");
			try{
				Class.forName(driver).newInstance();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			}catch (InstantiationException e) {
				e.printStackTrace();
			}
		}else if(IP_seleck==1){
			url = test_url;
			user = test_user;
			password = test_password;
			window.log("目前選擇的連線是測試主機 :10.40.140.188");
			try{
				Class.forName(driver); 
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}else{
			window.log("主機選擇失敗");
		}
		
		
		
		try{
			if(conn==null||conn.isClosed()){
				conn = DriverManager.getConnection(url, user, password);
			}else{
				conn.close();
				conn = DriverManager.getConnection(url, user, password);
			}
		}catch(SQLException e){
			e.printStackTrace();
			window.log("資料庫連線失敗!!");
		}
		
	}
	public static ArrayList db_getSO01(String _CA036){
		String _GG001=null;
		String _GG004=null;
		
		ArrayList SO01_list = new ArrayList();
		try{
			if(_CA036.equals("")){
				window.log("請輸入出貨單號");
			}else{
				String sql = 
						  "SELECT TOP 1 "
						+ "a.GG001,a.GG004 FROM CRMGG_PART a "
						+ "JOIN DSCSO_dscpwc b "
						+ "on b.SO04 = a.GG044 "
						+ "AND  b.SO01 ='"+ _CA036+ "' "
						+ "order by b.SO02 desc";
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery(sql);
				//處理db資料存成陣列
				while(rs.next())
				{ 
					_GG001 = rs.getString("GG001");
					_GG004 = rs.getString("GG004");
					
					SO01_list.add(_GG001);
					SO01_list.add(_GG004);
				}
				window.log("現在查詢的是 :"+_CA036);
				if(SO01_list.size()!=0){
					String sql_getCA039="SELECT max(CA039) FROM LICA WHERE CA001='"+_GG001+"' AND CA003='145'";
					//取得套數
					Integer CA039_int=null;
					Statement st = conn.createStatement();
					ResultSet rs_CA039 = st.executeQuery(sql_getCA039);
					while(rs_CA039.next()){ 
						CA039_int = rs_CA039.getInt(1);
						window.log("原套數="+CA039_int);
					}
					CA039_int=CA039_int+1;
					window.log("新套數="+(CA039_int));
					String CA039=String.valueOf(CA039_int);
					SO01_list.add(CA039);//將資料庫之套數資料取得並改寫前端
				}else{
					window.log("無此出貨單號，請檢查輸入的資料。");
				}
			}
		}catch(SQLException e){
			window.log("SQLException :"+e);
			window.log(_CA036);
		}
		
		return SO01_list;
	}
	public static ArrayList db_getGG001(String _GG001){
		String _GG004=null;
		ArrayList GG001_list = new ArrayList();
		
		try{
			if(_GG001.equals("")){
				window.log("請輸入客戶代號");
			}else{
				String sql = 
						  "SELECT TOP 1 "
						+ "GG004 FROM CRMGG_PART "
						+ "WHERE GG001 = '"+_GG001+"' ";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				//處理db資料存成陣列
				while(rs.next()){ 
					_GG004 = rs.getString("GG004");
					GG001_list.add(_GG004);
				}
				window.log("現在查詢的是 :"+_GG001);
			if(GG001_list.size()!=0){
				String sql_getCA039="SELECT max(CA039) FROM LICA WHERE CA001='"+_GG001+"' AND CA003='145'";
				//取得套數
				Integer CA039_int=null;
				Statement st;
				try {
					st = conn.createStatement();
					ResultSet rs_CA039 = st.executeQuery(sql_getCA039);
					window.log("現在取得套數:CA039");
					while(rs_CA039.next()){ 
						CA039_int = rs_CA039.getInt(1);
						window.log("原套數="+CA039_int);
					}
					window.log("新套數="+(CA039_int+1));
					String CA039=String.valueOf(CA039_int+1);
					GG001_list.add(CA039);//將資料庫之套數資料取得並改寫前端
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
					window.log("無此客戶代號，請檢查輸入的資料。");
				}
			}
		}catch(SQLException e){
			window.log("SQLException :"+e);
			window.log(_GG001);
		}
		
		return GG001_list;
	}
	public static String insert_sql(ArrayList insert_data){//取得CA002值
		boolean checkCA002=true;
		boolean checkCA039=true;
		Integer CA002_int=null;
		String CA002="";
		int CA039_int=0;
		
		String CA036=insert_data.get(0).toString();//出貨單號
		String CA001=insert_data.get(1).toString();//客戶代號
		String CA025=insert_data.get(2).toString();//客戶全名
		String CA034=insert_data.get(3).toString();//建檔人工號
		String CA008=insert_data.get(4).toString();//出貨日期
		String CA033=insert_data.get(4).toString();//出貨日期
		String CA039=insert_data.get(5).toString();//套數
		String CA040=insert_data.get(6).toString();//U數備註
		String CA007=insert_data.get(7).toString();//授權數量
		
		String sql_getCA002="SELECT max(CA002) FROM LICA WHERE CA001='"+CA001+"' AND CA003='145'";
		
		Statement stmt;
		try {
			//取得流水序號
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql_getCA002);
			
			while(rs.next()){ 
				CA002=rs.getString(1);
				window.log("資料庫中取得的流水序號:"+CA002);
			}
			//取出數值
			if(CA002==null){//初始值
				CA002="0001";
				window.log("CA002原欄位為空值 : "+CA002);
				CA002_int = Integer.parseInt(CA002);
			}else{//已有值得處理方式
				CA002=CA002.trim();
				CA002_int = Integer.parseInt(CA002)+1;
			}
			
			if((CA002_int/1000)>=1){//ex:1009
				CA002=""+String.valueOf(CA002_int);
			}else if((CA002_int/100)>=1){//ex:0259
				CA002="0"+String.valueOf(CA002_int);
			}else if((CA002_int/10)>=1){//ex:0049
				CA002="00"+String.valueOf(CA002_int);
			}else{//ex:0009
				CA002="000"+String.valueOf(CA002_int);
			}
			window.log("新增之流水序號:="+CA002);
			insert_data.add(CA002);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return CA002;
	}
	
	public static  void insert_sql(ArrayList insert_data,String CA002){//新增
		String CA036=insert_data.get(0).toString();//出貨單號
		String CA001=insert_data.get(1).toString();//客戶代號
		String CA025=insert_data.get(2).toString();//客戶全名
		String CA034=insert_data.get(3).toString();//建檔人工號
		String CA008=insert_data.get(4).toString();//出貨日期
		String CA033=insert_data.get(4).toString();//出貨日期
		String CA039=insert_data.get(5).toString();//套數
		String CA040=insert_data.get(6).toString();//U數備註
		String CA007=insert_data.get(7).toString();//授權數量
		
		window.log("現在執行的CA039 :"+CA039);
		if(!CA039.equals("")||!CA039.equals(null)||!CA002.equals("")){
			CA001 = CA001.replaceAll("'", "''");
			CA002 = CA002.replaceAll("'", "''");
			CA008 = CA008.replaceAll("'", "''");
			CA025 = CA025.replaceAll("'", "''");
			CA033 = CA033.replaceAll("'", "''");
			CA034 = CA034.replaceAll("'", "''");
			CA036 = CA036.replaceAll("'", "''");
			CA039 = CA039.replaceAll("'", "''");
			CA040 = CA040.replaceAll("'", "''");
			CA007 = CA007.replaceAll("'", "''");
			
			String sql= "INSERT INTO LICA "+
					"(CA001,CA002,CA003,CA004,CA007,CA008,CA025,CA033,CA034,CA036,CA039,CA040) "+
					"VALUES "+
					"(N'"+CA001+"',N'"+CA002+"',N'145',N'0',"+CA007+",N'"+CA008+"',N'"+CA025+"',N'"+CA033+"',N'"+CA034+"',N'"+CA036+"',"+CA039+",N'"+CA040+"')";
			try {
				Statement stmt = conn.createStatement();
				stmt.execute(sql);
				
				window.log("已執行命令 : 新增命令");
				window.log("現在執行的是 :"+sql);
			} catch (SQLException e) {
				window.log("現在執行的是 :"+sql);
				e.printStackTrace();
			}
		}else{
			window.log("CA039缺失");
		}
		
	}
	public static String getDateTime(){
		SimpleDateFormat sdFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		
		return strDate;
		}
}
