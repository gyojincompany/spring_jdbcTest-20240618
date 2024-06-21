package com.gyojincompany.jdbc.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.gyojincompany.jdbc.dao.MemberDao;
import com.gyojincompany.jdbc.dto.MemberDto;

public class MListCommand implements MCommand {

	@Override
	public int execute(Model model) {
		// TODO Auto-generated method stub
				
		MemberDao memberDao = new MemberDao();
		ArrayList<MemberDto> mDtos = memberDao.listMember();
		
		model.addAttribute("mDtos", mDtos);
		
		return 0;
	}

}
