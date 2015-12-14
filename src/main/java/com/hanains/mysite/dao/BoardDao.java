package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hanains.mysite.vo.BoardVo;

public class BoardDao {
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			//1.드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );

			//2.커넥션 만들기(ORACLE DB)
			String dbURL  = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection( dbURL, "webdb", "webdb" );
			
		} catch( ClassNotFoundException ex ){
			System.out.println( "드라이버 로딩 실패-" + ex );
		} 
		
		return connection;
	}
	
	public BoardVo get( Long no ) {
		BoardVo vo = null;
		try{
			//1. get Connection
			Connection conn = getConnection();
			
			//2. prepare statement
			String sql = 
				" select no, title, content, member_no, grp_no, seq_no, lvl" +
				"   from board" +
				"  where no=?";
			PreparedStatement pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setLong( 1, no );
			
			//4. execute SQL
			ResultSet rs = pstmt.executeQuery();
			if( rs.next() ) {
				vo = new BoardVo();
				vo.setNo( rs.getLong( 1 ) );
				vo.setTitle( rs.getString( 2 ) );
				vo.setContent( rs.getString( 3 ) );
				vo.setMemberNo( rs.getLong( 4 ) );
				vo.setGrp_no( rs.getLong( 5) );
				vo.setSeq_no( rs.getLong( 6 ) );
				vo.setLvl( rs.getLong( 7 ) );
			}
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}
		
		return vo;
	}

	public void increaseViewCount( Long no ) {
		try{
			//1. get Connection
			Connection conn = getConnection();
			
			//2. prepare statement
			String sql = 
				" update board" +
				"    set view_cnt = view_cnt + 1" +		
				"  where no=?";
			PreparedStatement pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setLong( 1, no );
			
			//4. execute SQL
			pstmt.executeUpdate();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}		
	}
	
	public List<BoardVo> getList(Long page) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		try {
			Connection conn = getConnection();
			String sql = "select * from (select a.no, a.title, a.member_no, b.name as member_name, a.view_cnt, to_char(a.reg_date,'yyyy-mm-dd hh:MM:ss'), "
					+ "a.lvl, rownum as rnum from board a, member b where a.member_no = b.no order by a.grp_no desc,seq_no) "
					+ "where rnum>=? and rnum<=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, 1+(page-1)*10);
			pstmt.setLong(2, page*10);
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ){
				Long no = rs.getLong( 1 );
				String title = rs.getString( 2 );
				Long memberNo = rs.getLong( 3 );
				String memberName = rs.getString( 4 );
				int viewCount = rs.getInt( 5 );
				String regDate = rs.getString( 6 );
				Long lvl = rs.getLong(7);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setMemberNo(memberNo);
				vo.setMemberName(memberName);
				vo.setViewCount(viewCount);
				vo.setRegDate(regDate);
				vo.setLvl(lvl);
				
				list.add( vo );
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}
		
		return list;
	}
	
	public void update( BoardVo vo ) {
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql =
				" update board" +
				"    set title=?," +
				"        content=?" +
				"  where no=?";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, vo.getTitle() );
			pstmt.setString( 2, vo.getContent() );
			pstmt.setLong( 3, vo.getNo() );
			
			//4. query 실행
			pstmt.executeUpdate();
			
			//5. 자원정리
			pstmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}		
	}
	
	public void delete( Long no ) {
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql = "delete from board where no = ?";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			
			//3. binding
			pstmt.setLong( 1, no );
			
			//4. query 실행
			pstmt.executeUpdate();
			
			//5. 자원정리
			pstmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}		
	}
	
	public void insert( BoardVo vo ) {
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql = 
				" insert" +
				"   into board" +
				" values ( board_no_seq.nextval, ?, ?, ?, 0, SYSDATE, board_no_seq.currval, 1, 0 )";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, vo.getTitle() );
			pstmt.setString( 2, vo.getContent() );
			pstmt.setLong( 3, vo.getMemberNo() );
			
			//4. query 실행
			pstmt.executeUpdate();
			
			//5. 자원정리
			pstmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}		
	}

	public List<BoardVo> getList(String category, String key, Long page) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		try {
			Connection conn = getConnection();

			String keyword = "%" + key + "%";
			
			String sql =
				"select * from (select a.no, a.title, a.member_no, b.name as member_name, a.view_cnt, to_char(a.reg_date,'yyyy-mm-dd hh:MM:ss'), "
				+ "a.lvl, rownum as rnum from board a, member b where a.member_no = b.no and "  
				+ category + " like ? order by a.grp_no desc,seq_no) "
				+ "where rnum>=? and rnum<=?";
		
			PreparedStatement pstmt = conn.prepareStatement( sql );
			//3. binding
			pstmt.setString(1,keyword);
			pstmt.setLong(2, 1+(page-1)*10);
			pstmt.setLong(3, page*10);
			
			//4. execute SQL
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ){
				Long no = rs.getLong( 1 );
				String title = rs.getString( 2 );
				Long memberNo = rs.getLong( 3 );
				String memberName = rs.getString( 4 );
				int viewCount = rs.getInt( 5 );
				String regDate = rs.getString( 6 );
				Long lvl = rs.getLong(7);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setMemberNo(memberNo);
				vo.setMemberName(memberName);
				vo.setViewCount(viewCount);
				vo.setRegDate(regDate);
				vo.setLvl(lvl);
				
				list.add( vo );
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}
		
		return list;
	}
	
	public void replyupdate(BoardVo vo) {
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql =
				"update board set seq_no = seq_no + 1 where grp_no=? and seq_no>?" ;
			PreparedStatement pstmt1 = connection.prepareStatement( sql );
			
			//3. binding
			pstmt1.setLong( 1, vo.getGrp_no());
			pstmt1.setLong( 2, vo.getSeq_no());			
			
			//4. query 실행
			pstmt1.executeUpdate();
				System.out.println("업데이트까진성공");
			sql = "insert into board values (board_no_seq.nextval, ?, ?, ?, 0, "
					+ "SYSDATE, ?, ?, ? )";
			PreparedStatement pstmt2 = connection.prepareStatement( sql );
			
			//3. binding
			pstmt2.setString( 1, vo.getTitle() );
			pstmt2.setString( 2, vo.getContent() );
			pstmt2.setLong( 3, vo.getMemberNo() );
			pstmt2.setLong( 4, vo.getGrp_no());
			pstmt2.setLong( 5, vo.getSeq_no() + 1);
			pstmt2.setLong( 6, vo.getLvl() + 1);
			
			//4. query 실행
			pstmt2.executeUpdate();
			
			//5. 자원정리
			pstmt2.close();
			connection.close();
			
			
			
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}		
	}
	
	public void pagenation(BoardVo vo) {
	
	}
	
	public Long getLength(){
		Long length = null;	
		try{
			//1. get Connection
			Connection conn = getConnection();
			
			//2. prepare statement
			String sql = "select count(*) "
					+ "from (select a.no, a.title, a.member_no, b.name as member_name, a.view_cnt, to_char(a.reg_date,'yyyy-mm-dd hh:MM:ss'), "
					+ "a.lvl from board a, member b where a.member_no = b.no order by a.reg_date desc,seq_no)";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			length = rs.getLong(1);
			System.out.println("검색어없을때 호출중 length는 : " + length);
			rs.close(); 
			stmt.close();
			conn.close();
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}
		return length;
	}
	
	public Long getLength(String category, String key){
		Long length = null;	
		try{
			//1. get Connection
			Connection conn = getConnection();
			
			//2. prepare statement
			String keyword = "%" + key + "%";
			
			String sql =
				"select count(*) "
				+ "from (select a.no, a.title, a.member_no, b.name as member_name, a.view_cnt, "
				+ "to_char(a.reg_date,'yyyy-mm-dd hh:MM:ss'), a.lvl from board a, member b where a.member_no = b.no"
				+ " and " + category + " like ? order by a.reg_date desc,seq_no)";
						
			PreparedStatement pstmt = conn.prepareStatement( sql );
			//3. binding
			pstmt.setString( 1, keyword);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			length = rs.getLong(1);
			System.out.println("검색어잇을때 호출중");
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}
		return length;
	}
}