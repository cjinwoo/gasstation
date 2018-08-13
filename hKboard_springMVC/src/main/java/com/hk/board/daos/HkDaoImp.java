package com.hk.board.daos;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.HkDto;

@Repository
public class HkDaoImp implements IHKDao{

	
	//@Autowired:객체를 xml에 등록한뒤, new하지않고 객체를 사용할수 있게 한다.
	@Autowired
	private SqlSessionTemplate sqlSession;//객체선언
	
	private String namespace="com.hk.board.";
	
	@Override
	public List<HkDto> getAllBoard() {
		return sqlSession.selectList(namespace+"boardlist");
	}

	@Override
	public HkDto getboard(int seq) {
		return sqlSession.selectOne(namespace+"getboard",seq);
	}

	@Override
	public boolean insertBoard(HkDto dto) {
		int count=0;
		count=sqlSession.insert(namespace+"insertboard",dto);
		return count>0?true:false;
	}

	@Override
	public boolean updateBoard(HkDto dto) {
		int count=0;
		count=sqlSession.delete(namespace+"updateboard", dto);
		return count>0?true:false;
	}

	@Override
	public boolean deleteBoard(int seq) {
		int count=0;
		count=sqlSession.delete(namespace+"delboard", seq);
		return count>0?true:false;
	}

	
}
