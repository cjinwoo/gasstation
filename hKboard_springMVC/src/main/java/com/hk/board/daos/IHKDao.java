package com.hk.board.daos;

import java.util.List;

import com.hk.board.dtos.HkDto;

public interface IHKDao {

	public List<HkDto> getAllBoard();
	public HkDto getboard(int seq);
	public boolean insertBoard(HkDto dto);
	public boolean updateBoard(HkDto dto);
	public boolean deleteBoard(int seq);
}
