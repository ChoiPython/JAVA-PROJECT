import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class DBA {
	private Connection conn;
	//DB 접속을 위한 주소, 아이디, 비밀번호
	private static String dburl= "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";;
	private static String dbUser= "testjju";
	private static String dbpw="1234";
	
	
	//모든 유저 조회 : 관리자 페이지의 리스트에 들어갈 예정 //유저클래스는 유저클래스네임으로 바꿀것
	//조회 메소드는 추가 수정 삭제와 다르게 인자는 리스트가 들어갈 예정, 데이터 받아와야하니까
	public void selectAllData(ArrayList<User> list) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="Select * from user where 사원번호!=0";
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				User user=new User();
				user.setId(rs.getInt("사원번호"));
				user.setName(rs.getString("사원이름"));
				user.setDepart(rs.getString("부서"));
				user.setRank(rs.getString("직급"));
				user.setHalfway(rs.getInt("반차"));
				user.setPoint(rs.getInt("포인트"));
				list.add(user);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		}catch(SQLException e) {}
	}
	//유저 조회
	//조회에 사용할 예정
	public void selectNameData(ArrayList<User> list,String name) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="Select * from user where 사원이름=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,name);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				User user=new User();
				user.setId(rs.getInt("사원번호"));
				user.setName(rs.getString("사원이름"));
				user.setDepart(rs.getString("부서"));
				user.setRank(rs.getString("직급"));
				user.setHalfway(rs.getInt("반차"));
				user.setPoint(rs.getInt("포인트"));
				list.add(user);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		}catch(SQLException e) {}
	}
	//아이디로 조회
	public User selectIdData(int id) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		User user=new User();
		String sql="Select * from user where 사원번호=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				user.setId(rs.getInt("사원번호"));
				user.setName(rs.getString("사원이름"));
				user.setDepart(rs.getString("부서"));
				user.setRank(rs.getString("직급"));
				user.setHalfway(rs.getInt("반차"));
				user.setPoint(rs.getInt("포인트"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		}catch(SQLException e) {}
		return user;
	}
	//유저를 추가 : 관리자 페이지의 추가에 들어갈 예정, 유저 클래스의 필드의 모든 값을 입력받는다.
	public void insertData(int id,String name, String depart, String rank, int halfway, int point,String imgaddr) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="insert into user values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, depart);
			pstmt.setString(4, rank);
			pstmt.setInt(5, halfway);
			pstmt.setInt(6, point);
			pstmt.setString(7, imgaddr);
			
			int result= pstmt.executeUpdate();
			if(result==1) {
				System.out.println("insert complete");
			}
		}catch(Exception e) {
			System.out.println("insert failed");
		}finally {
			try {
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch (Exception e) {}
		}
		try {
			conn.close();
		}catch(SQLException e) {}
	}
	//유저 클래스를 수정 : 관리자 페이지의 수정에 들어갈 예정, id는 수정할 수 없다. id가 수정을 위한 고유한 키이기 때문
	//추가와 마찬가지로 모든 데이터를 입력 받는다.
	//수정을 위한 기존 데이터 불러오기 추가하여 폼 불러올때 기본값 입력되게 한 후 변경할 수 있게 하면 될듯
	public void updateData(int id,String name, String depart, String rank,int halfway, int point,String imgaddr) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="update user set 사원이름=?, 부서=?, 직급=?, 포인트=?, 사진=? where 사원번호=?";
		PreparedStatement pstmt=null;
		//폼에서 데이터 받아올 코드 작성
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, depart);
			pstmt.setString(3, rank);
			pstmt.setInt(4, halfway);
			pstmt.setInt(5, point);
			pstmt.setString(6, imgaddr);
			pstmt.setInt(7, id);
			
			int result= pstmt.executeUpdate();
			if(result==1) {
				System.out.println("update complete");
			}
		}catch(Exception e) {
			System.out.println("update failed");
		}finally {
			try {
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch (Exception e) {}
		}
		try {
			conn.close();
		}catch(SQLException e) {}
	}
	public void updatecoin(int id, int point) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="update user set 포인트=? where 사원번호=?";
		PreparedStatement pstmt=null;
		//폼에서 데이터 받아올 코드 작성.
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setInt(2, id);
			
			int result= pstmt.executeUpdate();
			if(result==1) {
				System.out.println("update complete");
			}
		}catch(Exception e) {
			System.out.println("update failed");
		}finally {
			try {
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch (Exception e) {}
		}
		try {
			conn.close();
		}catch(SQLException e) {}
	}
	//유저 삭제 : 관리자 클래스의 삭제에 들어갈 예정, id만 입력받아 삭제할 수 있다.
	public void deleteData(int id) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="delete from user where 사원번호=?";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result= pstmt.executeUpdate();
			if(result==1) {
				System.out.println("delete complete");
			}
		}catch(Exception e) {
			System.out.println("delete failed");
		}finally {
			try {
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch (Exception e) {}
		}
		try {
			conn.close();
		}catch(SQLException e) {}
	}
	//로그인용 메소드 : imgaddr 추가해야함
	public User login(String ids,String name) {
		int id=Integer.parseInt(ids);
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		User user=new User();
		
		String sql="Select * from user where 사원번호=? and 사원이름=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.setString(2,name);
			ResultSet rs=pstmt.executeQuery();

			
			while(rs.next()) {
				user.setId(rs.getInt("사원번호"));
				user.setName(rs.getString("사원이름"));
				user.setDepart(rs.getString("부서"));
				user.setRank(rs.getString("직급"));
				user.setHalfway(rs.getInt("반차"));
				user.setPoint(rs.getInt("포인트"));
				user.setRank(rs.getString("사진"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		}catch(SQLException e) {}
		return user;
	}
	//메인함수는 폼 입출력에 참고하고 추후에 지울 예정
//	public static void main(String[] args) {
//		Scanner sc=new Scanner(System.in);
//		DBA db= new DBA();
//		boolean flag=true;
//		while(flag) {
//			int id,halfway,reword,point;
//			String name,depart,rank;
//			System.out.println("1.추가 2.수정 3. 삭제");
//			int c=sc.nextInt();
//			switch(c) {
//			case 1:
//				sc.nextLine();
//				System.out.print("사번 이름 부서 직급 반차 상벌점 포인트");
//				String s=sc.nextLine();
//				StringTokenizer st=new StringTokenizer(s," ");
//				
//				id=Integer.parseInt(st.nextToken());
//				name=st.nextToken();
//				depart=st.nextToken();
//				rank=st.nextToken();
//				halfway=Integer.parseInt(st.nextToken());
//				reword=Integer.parseInt(st.nextToken());
//				point=Integer.parseInt(st.nextToken());
//				
//				db.insertData(id,name,depart,rank,halfway,reword,point);
//				break;
//			case 2:
//				sc.nextLine();
//				System.out.print("사번 이름 부서 직급 반차 상벌점 포인트");
//				
//				String s1=sc.nextLine();
//				StringTokenizer st1=new StringTokenizer(s1," ");
//				
//				id=Integer.parseInt(st1.nextToken());
//				name=st1.nextToken();
//				depart=st1.nextToken();
//				rank=st1.nextToken();
//				halfway=Integer.parseInt(st1.nextToken());
//				reword=Integer.parseInt(st1.nextToken());
//				point=Integer.parseInt(st1.nextToken());
//
//				db.updateData(id,name,depart,rank,halfway,reword,point);
//				break;
//			case 3:
//				sc.nextLine();
//				System.out.print("사번 ");
//				int n=sc.nextInt();
//				db.deleteData(n);
//				break;
//			default :
//				System.out.println("종료합니다.");
//				flag=false;
//				break;
//			}
//		}
//	}
}