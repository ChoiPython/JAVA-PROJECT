import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class DBA {
	private Connection conn;
	//DB 접속을 위한 주소, 아이디, 비밀번호
	private static String dburl= "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";;
	private static String dbUser= "test";
	private static String dbpw="1234";
	
	
	//모든 유저 조회 : 관리자 페이지의 리스트에 들어갈 예정 //유저클래스는 유저클래스네임으로 바꿀것
	//조회 메소드는 추가 수정 삭제와 다르게 인자는 리스트가 들어갈 예정, 데이터 받아와야하니까
	public void selectAllData(ArrayList<User2> list,Date date) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="select Attendance.사원번호, user.사원이름, count(case when Attendance.상태='출근' then 1 end) as '출근', count(case when Attendance.상태='지각' then 1 end) as '지각', count(case when Attendance.상태='결근' then 1 end) as '결근' from Attendance join user on user.사원번호 = Attendance.사원번호 where mid(Attendance.날짜,1,7)=date_format(?,'%Y-%m') group by 사원번호";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setDate(1,date);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				User2 user=new User2();
				user.setId(rs.getInt("사원번호"));
				user.setName(rs.getString("사원이름"));
				user.setAttendance(rs.getInt("출근"));
				user.setTardy(rs.getInt("지각"));
				user.setAbsence(rs.getInt("결근"));
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
	public void selectNameData(ArrayList<User2> list,Date date, String name) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="select Attendance.사원번호, user.사원이름, count(case when Attendance.상태='출근' then 1 end) as '출근', count(case when Attendance.상태='지각' then 1 end) as '지각', count(case when Attendance.상태='결근' then 1 end) as '결근' from Attendance join user on user.사원번호 = Attendance.사원번호 where user.사원이름 = ? and mid(Attendance.날짜,1,7)=date_format(?,'%Y-%m') group by 사원번호";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setDate(2,date);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				User2 user=new User2();
				user.setId(rs.getInt("사원번호"));
				user.setName(rs.getString("사원이름"));
				user.setAttendance(rs.getInt("출근"));
				user.setTardy(rs.getInt("지각"));
				user.setAbsence(rs.getInt("결근"));
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
				user.setImgaddr(rs.getString("사진"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		}catch(SQLException e) {}
		return user;
	}
	public int TOCount(Date d) {
		int to=0;
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		User user=new User();
		String sql="select count(*) as 'TO' from Attendance where 날짜=? and (상태='휴가' or 상태='반차')";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setDate(1,d);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				to=rs.getInt("TO");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		}catch(SQLException e) {}
		return to;
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
	      }catch(SQLIntegrityConstraintViolationException e) {
	         throw new RuntimeException(e);
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
	 //결근체크
	 public void insertAbsence(Date date) {
			ArrayList<Integer> list=new ArrayList<>();
			try {
				System.out.println("db로딩중");
				conn=DriverManager.getConnection(dburl, dbUser, dbpw);
			}catch(Exception e) {
				System.out.println("db로딩 실패");
			}
			String sql="select 사원번호 from user";
			PreparedStatement pstmt=null;
			try {
				pstmt=conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					int n=rs.getInt("사원번호");
					list.add(n);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			insertAbsence2(date,list);
	 }
	 public void insertAbsence2(Date d,ArrayList<Integer> list) {
	      try {
	         System.out.println("db로딩중");
	         conn=DriverManager.getConnection(dburl, dbUser, dbpw);
	      }catch(Exception e) {
	         System.out.println("db로딩 실패");
	      }
	      String sql="insert into Attendance values(?,?,'결근')";
	      PreparedStatement pstmt=null;
	      
	      for(int i=1;i<list.size();i++) {
				try {
					int n=list.get(i);
					pstmt=conn.prepareStatement(sql);
					pstmt.setDate(1,d);
					pstmt.setInt(2,n);
					int result= pstmt.executeUpdate();
			         if(result==1) {
			            System.out.println("insert complete");
			         }
				} catch (SQLException e1) {
					//e1.printStackTrace();
				}
			}
	        try {
	            if(pstmt!=null && !pstmt.isClosed()) {
	               pstmt.close();
	            }
	         }catch (Exception e) {}
	      try {
	         conn.close();
	      }catch(SQLException e) {}
	 }
	//유저 클래스를 수정 : 관리자 페이지의 수정에 들어갈 예정, id는 수정할 수 없다. id가 수정을 위한 고유한 키이기 때문
	//추가와 마찬가지로 모든 데이터를 입력 받는다.
	//수정을 위한 기존 데이터 불러오기 추가하여 폼 불러올때 기본값 입력되게 한 후 변경할 수 있게 하면 될듯
	public int updateData(int id,String name, String depart, String rank,int halfway, int point,String imgaddr) {
		int result=0;
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="update user set 사원이름=?, 부서=?, 직급=?,반차=?, 포인트=?, 사진=? where 사원번호=?";
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
			
			result= pstmt.executeUpdate();
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
		return result;
	}
	public void updatehalfway(int id, int halfway) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="update user set 반차=? where 사원번호=?";
		PreparedStatement pstmt=null;
		//폼에서 데이터 받아올 코드 작성.
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, halfway);
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
	// 유저 반차 업데이트 - 상점에서 반차 & 휴가 구매
	public void Buyhalf(int id, int half) {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="update user set 반차=반차+? where 사원번호=?";
		PreparedStatement pstmt=null;
		//폼에서 데이터 받아올 코드 작성.
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, half);
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
	public int deleteData(int id) {
		int result=0;
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="delete from Attendance where 사원번호=?";
		PreparedStatement pstmt=null;
		//Attendance 삭제 : 이거 삭제 안하면 유저 삭제가 안됨
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result= pstmt.executeUpdate();
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
		//user삭제
		sql="delete from user where 사원번호=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result= pstmt.executeUpdate();
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
		return result;
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
				user.setImgaddr(rs.getString("사진"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
		}catch(SQLException e) {}
		return user;
	}
	public int leaveApplication(int id,Date d,String s) {
		int result=0;
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="insert into Attendance values(?,?,?)";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setDate(1, d);
			pstmt.setInt(2, id);
			pstmt.setString(3, s);
			
			
			result= pstmt.executeUpdate();
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
		return result;
	}
	
	// 상점 데이터 설정
	int percent;
	int rprice;
	int hprice;
	public void SetShop(int percent, int rprice, int hprice) {
		this.percent = percent;
		this.rprice = rprice;
		this.hprice = hprice;
		
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql="update shop set 뽑기확률=?, 뽑기가격=?, 반차가격=?";
		PreparedStatement pstmt=null;
		//폼에서 데이터 받아올 코드 작성.
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, percent);
			pstmt.setInt(2, rprice);
			pstmt.setInt(3, hprice);
			
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
	// 상점 데이터 반환
	public int[] GetShop() {
		try {
			System.out.println("db로딩중");
			conn=DriverManager.getConnection(dburl, dbUser, dbpw);
		}catch(Exception e) {
			System.out.println("db로딩 실패");
		}
		String sql = "Select * from shop";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();

			
			while(rs.next()) {
				percent = rs.getInt(1);
				rprice = rs.getInt(2);
				hprice = rs.getInt(3);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// 뽑기 확률, 뽑기 가격, 반차 가격
		System.out.println(percent +"" + rprice + "" + hprice);
		try {
			conn.close();
		}catch(SQLException e) {}
		return new int [] {percent, rprice, hprice};
	}
	
	
}