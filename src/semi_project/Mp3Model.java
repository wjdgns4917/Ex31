package semi_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Mp3Model {
	Connection con;
	public Mp3Model() throws Exception {
		con=DBCon.getConnection();
	}
	public Mp3 selectbyPk(int no) throws Exception {
		Mp3 m=new Mp3();
		String sql="select * from mp3info where plno="+no;
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while (rs.next()) {
			m.setPLNO(Integer.parseInt(rs.getString("PLNO")));
			m.setMtitle(rs.getString("MTITLE"));
			m.setAtitle(rs.getString("ATITLE"));
			m.setSinger(rs.getString("SINGER"));
			m.setGenre(rs.getString("GENRE"));
			m.setRtime(rs.getString("RTIME"));
			m.setRdate(rs.getString("RDATE"));
			m.setAC(rs.getString("AC"));
		}
		rs.close();
		pstmt.close();
		return m;
	}
	public void insertMp3(Mp3 m) throws Exception {
		con.setAutoCommit(false);		
			String sql1="INSERT INTO mp3info " + 
					"VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement ps1=con.prepareStatement(sql1);
			ps1.setInt(1, m.getPLNO());
			ps1.setString(2, m.getMtitle());
			ps1.setString(3, m.getAtitle());
			ps1.setString(4, m.getSinger());
			ps1.setString(5, m.getGenre());
			ps1.setString(6, m.getRtime());
			ps1.setString(7, m.getRdate());
			ps1.setString(8, m.getAC());
									
			//실행
			int r1=ps1.executeUpdate();
									
			if (r1!=1) {
				con.rollback();
			}
			con.commit();
			System.out.println("입력 완료");
			ps1.close();					
	}
	
	public void modifyMp3(Mp3 m) throws Exception {
		String sql="UPDATE MP3INFO SET MTITLE=?,ATITLE=?,"
				+"SINGER=?,GENRE=?,RTIME=?,RDATE=?,AC=? WHERE PLNO=?";
		PreparedStatement ps=con.prepareStatement(sql);
		
		if (m.getMtitle()==null) {
			m.setMtitle("미정");
		}
	
		ps.setString(1, m.getMtitle());
		ps.setString(2, m.getAtitle());
		ps.setString(3, m.getSinger());
		ps.setString(4, m.getGenre());
		ps.setString(5, m.getRtime());
		ps.setString(6, m.getRdate());
		ps.setString(7, m.getAC());
		ps.setInt(8, m.getPLNO());
		
		ps.executeUpdate();//실행
		ps.close();
	}
	public void delete(Mp3 m) throws Exception {
		con.setAutoCommit(false);
		String sql="delete from mp3info where plno=?";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, m.getPLNO());
		
		int r1=ps.executeUpdate();
		if (r1!=1) {
			con.rollback();
		}
		con.commit();
		ps.close();
		con.setAutoCommit(true);
		
	}
	public ArrayList searchMp3(int idx, String str) throws Exception {
		//데이터베이스 mp3 테이블에서 검색 -> 담아서(arraylist)
		//JTable로 전달
		String[] key= {"mtitle","singer"};
		String sql="SELECT PLNO,MTITLE,ATITLE,SINGER,GENRE,RTIME,RDATE FROM MP3INFO"
				+ " where "+key[idx]+" like '%"+str+"%'";
		System.out.println(sql);
		
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		ArrayList data=new ArrayList<>();
		while (rs.next()) {
			ArrayList temp=new ArrayList<>();
			temp.add(rs.getString("plno"));
			temp.add(rs.getString("mtitle"));
			temp.add(rs.getString("atitle"));
			temp.add(rs.getString("singer"));
			temp.add(rs.getString("genre"));
			temp.add(rs.getString("rtime"));
			temp.add(rs.getString("rdate"));			
			data.add(temp);
		}
		rs.close();
		ps.close();
	
		return data;
	}
}
