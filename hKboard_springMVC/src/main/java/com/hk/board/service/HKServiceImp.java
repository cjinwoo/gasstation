package com.hk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.daos.IHKDao;
import com.hk.board.dtos.HkDto;

@Service
public class HKServiceImp implements IHKService{

	@Autowired
	public IHKDao hkdaoImp;
	
	@Override
	public List<HkDto> getAllBoard() {
		return hkdaoImp.getAllBoard();
	}

	@Override
	public HkDto getBoard(int seq) {
		return hkdaoImp.getboard(seq);
	}

	@Override
	public boolean insertBoard(HkDto dto) {
		return hkdaoImp.insertBoard(dto);
	}

	@Override
	public boolean updateBoard(HkDto dto) {
		return hkdaoImp.updateBoard(dto);
	}

	@Override
	public boolean deleteBoard(int seq) {
		return hkdaoImp.deleteBoard(seq);
	}

	
}
