package com.gyojincompany.jdbc;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.jdbc.command.MDeleteCommand;
import com.gyojincompany.jdbc.command.MJoinCommand;
import com.gyojincompany.jdbc.dao.MemberDao;
import com.gyojincompany.jdbc.dto.MemberDto;

@Controller
public class JdbcController {
	
	@RequestMapping(value = "/test")
	public void test() {
		
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/spring_projectdb";
		String username = "root";
		String password = "12345";
		
		Connection conn = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		MJoinCommand command = new MJoinCommand();
		int success = command.execute(model);
		
//		String mid = request.getParameter("mid");
//		String mpw = request.getParameter("mpw");
//		String mname = request.getParameter("mname");
//		String memail = request.getParameter("memail");
		
//		MemberDao memberDao = new MemberDao();
		
//		int success = memberDao.joinMember(mid, mpw, mname, memail);
		//success 값이 1이면 sql문 실행 성공 아니면 실패
		
		if(success == 1 ) { //회원 가입 성공			
			model.addAttribute("mid", request.getParameter("mid"));
			model.addAttribute("mname", request.getParameter("mname"));
			
			return "joinOk";
		} else {
			model.addAttribute("error", "회원가입이 실패하였습니다. 다시 시도해주세요.");
			
			return "join";
		}
		
		
	}
	
	@RequestMapping(value = "/delete")
	public String delete() {
		return "delete";
	}
	
	@RequestMapping(value = "/deleteOk")
	public String deleteOk(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		MDeleteCommand command = new MDeleteCommand();
		int success = command.execute(model);
		
		if(success == 1) {//회원 탈퇴 성공
			
			model.addAttribute("message", "회원 탈퇴 성공! 안녕히가세요!");
			
		} else { //회원 탈퇴 실패
			
			model.addAttribute("message", "회원 탈퇴 실패! 다시확인해 주세요!");
			
		}
		
		return "deleteOk";
	}
	
	
	

}
