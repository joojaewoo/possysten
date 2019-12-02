package pos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class db {
	static Statement stmt;
	static ResultSet rs;
	public db() {
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/pos";
            conn = DriverManager.getConnection(url, "root", "1234");
            stmt = (Statement) conn.createStatement();
            System.out.println("연결 성공");
            }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException  e){
            System.out.println("에러: " + e);
        }
	}
	 public static void main(String[] args) {
	     new db();
		 new gui();
	    	    //insert_B("201911071","콜라","1500","2");
	    		//new login();
//	            System.out.println(profit("20191107"));
		 
//	            System.out.println(chk_Bill_num("20191107"));
	            /*
	             TODO 
	             	여기서 GUI 객체 생성해서 뛰우기 (로그인 부터 시작);
	             */
//	        finally{
//	            try{
//	                if( conn != null && !conn.isClosed()){
//	                    conn.close();
//	                }
//	            }
//	            catch( SQLException e){
//	                e.printStackTrace();
//	            }
//	        }
	    }
	 static public int loginchk(String id,String pwd) { //로그인 확인
		 String sql= "select * from user where ID='"+id+"'and PWD='"+pwd+"'";
		 try {
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				if( rs.getBoolean("Enable"))
					return 1;
				else
					return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return -1;
	 }
	 static public boolean update_S(String name, int num) { // 재고수정 ( 환불 or 재고추가)
		 String sql= "select * from product where Product_Name='"+name+"'";
		 try {
			 rs=stmt.executeQuery(sql);
			 String stock="";
			 while(rs.next())
			 stock=rs.getString("Stock");
			 num=Integer.parseInt(stock)+num;
			 String sql1="Update product set stock ='"+num+"' where Product_Name='"+name+"'";
			 try {
				 int rss=stmt.executeUpdate(sql1);
				 return true;
			 }catch (SQLException e) {
					e.printStackTrace();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return false;
	 }
	 static public boolean chk_P(String name) {
		 String sql= "select count(*) AS count from product where Product_Name = '"+name+"'";
		 try {
				rs=stmt.executeQuery(sql);
				int cnt=0;
				while(rs.next()) {
				cnt=rs.getInt("count");
				}
				if(cnt<1)
					return false;
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace(System.out);
				e.printStackTrace();
			}
		 return false;
	 }
	 static public boolean insert_P(String barcode,String name,String price,String stock,String category) { //물건추가
		 String sql= "insert into product values ('"+barcode+"','"+name+"','"+price+"','"+stock+"','"+category+"')";
		 try {
				int rss=stmt.executeUpdate(sql);
				return true;
			} catch (SQLException e) {
				e.printStackTrace(System.out);
				e.printStackTrace();
			}
		 return false;
	 }
	 static public boolean insert_B(String barcode,String name,String price,String num,String category) { //영수증 생성
		 String sql= "insert into bill values ('"+barcode+"','"+price+"','"+name+"','"+num+"','"+category+"')";
		 try {
				int rss=stmt.executeUpdate(sql);
				return true;
			} catch (SQLException e) {
				e.printStackTrace(System.out);
				e.printStackTrace();
			}
		 return false;
	 }
	 static public boolean delete_B(String barcode) { // 영수증 삭제
		 String sql= "delete from bill where Bill_Barcode='"+barcode+"'";
		 try {
				int rss=stmt.executeUpdate(sql);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return false;
	 }
	 static public product search_L(String barcode){ // 리스트 만들기
		 String sql="select * from product where Product_Barcode='"+barcode+"'";
		 product product = null;
		 try {
			 rs=stmt.executeQuery(sql);
			 while(rs.next()) {
				 product= new product(rs.getString("Product_Barcode"),rs.getString("Product_Name"),rs.getString("Product_Price"),rs.getString("Stock"),rs.getString("Category"));
				 //product => 물품정보 담을 arraylist
			 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return product;
	 }
	 static public ArrayList<product> search_List(String name,String category){ // 리스트 만들기
		 String sql="";
		 if(!name.contentEquals("0")&&!category.contentEquals("0"))
		 sql="select * from product where Product_Name like '%"+name+"%' and Category ='" + category+"'";
		 else if(!name.contentEquals("0")&&category.contentEquals("0"))
			 sql="select * from product where Product_Name like '%"+name+"%'";
		 else if(name.contentEquals("0")&&!category.contentEquals("0"))
			 sql="select * from product where Category ='" + category+"'";
		 else 
			 sql="select * from product";
		 ArrayList<product> product = new ArrayList<product>();
		 try {
			 rs=stmt.executeQuery(sql);
			 while(rs.next()) {
				 product.add(new product(rs.getString("Product_Barcode"),rs.getString("Product_Name"),rs.getString("Product_Price"),rs.getString("Stock"),rs.getString("Category")));
				 //product => 물품정보 담을 arraylist
			 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return product;
	 }
	 static public ArrayList<bill> search_B(String barcode){ // 환불 영수증 찾기
	 String sql="select * from Bill where Bill_Barcode ='"+barcode+"'";
	 ArrayList<bill> bill= new ArrayList<bill>();
	 try {
		 rs=stmt.executeQuery(sql);
		 while(rs.next()) {
			 bill.add(new bill(rs.getString("Bill_Barcode"),rs.getString("Product_Name"),rs.getString("Total_Price"),rs.getString("Product_Count"),rs.getString("Product_Category")));
			 //bill => 물품정보 담을 arraylist
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 return bill;// => arraylist 리턴
	}
	 static public ArrayList<sale> Bill_L(String date) { //매출액 보기
		 ArrayList<sale> a=new ArrayList<sale>();
		 String sql="select Bill_Barcode, sum(Total_Price) AS Price from bill where Bill_Barcode like '"+date+"%' group by Bill_Barcode";
		 try {
			 rs=stmt.executeQuery(sql);
			 while(rs.next()) {
				 a.add(new sale(rs.getString("Bill_Barcode"),rs.getInt("Price")));
			 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return a;
	 }
	 static public ArrayList<sale> profit(String date) { //매출액 보기
		 ArrayList<sale> a=new ArrayList<sale>();
		 String sql="select * from bill where Bill_Barcode like '"+date+"%' order by Bill_Barcode asc";
		 try {
			 rs=stmt.executeQuery(sql);
			 while(rs.next()) {
				 a.add(new sale(rs.getString("Product_Category"),rs.getInt("Total_Price")));
			 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return a;
	 }
	 static public ArrayList<String> date_B(String date) { //영수증 바코드 리턴(날짜 받아서)
		 ArrayList <String> a=new ArrayList<String>();
		 String sql="select distinct * from bill where Bill_Barcode like '"+date+"%'";
		 try {
			 rs=stmt.executeQuery(sql);
			 while(rs.next()) {
				 a.add(rs.getString("Bill_Barcode"));
			 }
			 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return a;
	 }
	 static public int chk_Bill_num(String date) { // 빌 번호 확인
		 String sql="select * from Bill where Bill_Barcode like '"+date+"%' order by Bill_Barcode desc";
		 try {
			 rs=stmt.executeQuery(sql);
			 if(rs.next())
			 return rs.getInt("Bill_Barcode");
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		 return 0;
	 }
}