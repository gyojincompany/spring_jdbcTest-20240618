package com.gyojincompany.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.gyojincompany.jdbc.dto.MemberDto;

public class MemberDao {
	
	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/spring_projectdb";
	String username = "root";
	String password = "12345";
	
	//회원가입, 회원탈퇴, 회원리스트조회, 회원검색, 회원정보수정
	
	//1. 회원가입
	public int joinMember(String mid, String mpw, String mname, String memail) { //회원가입 메소드
		
		String sql = "INSERT INTO members(mid, mpw, mname, memail) VALUES(?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int success = 0;
		
		try {			
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			pstmt.setString(3, mname);
			pstmt.setString(4, memail);			
			
			success = pstmt.executeUpdate();//sql문 실행->1이 반환되면 성공 아니면 실패
			
		} catch (Exception e) {			
			e.printStackTrace();
			
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return success;
		
	}
}
