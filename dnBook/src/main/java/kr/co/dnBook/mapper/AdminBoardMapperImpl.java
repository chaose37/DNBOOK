package kr.co.dnBook.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.dnBook.vo.BoardCommentVO;
import kr.co.dnBook.vo.BoardFileVO;
import kr.co.dnBook.vo.BoardSearchVO;
import kr.co.dnBook.vo.BoardVO;


@Repository
public class AdminBoardMapperImpl implements AdminBoardMapper{

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	private static final String NAMESPACE = "kr.co.dnBook.mapper.AdminBoardMapper"; 
	
	@Override
	public List<BoardVO> selectList(BoardSearchVO boardSearch) throws Exception{
		return sqlSessionTemplate.selectList(NAMESPACE + ".selectList", boardSearch);
	}
	
	@Override
	public int selectTotalCount(BoardSearchVO boardSearch) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".selectTotalCount", boardSearch);
	}
	
	@Override
	public BoardVO selectDetail(int no) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".selectBoardDetail", no);
	}
	
	@Override
	public BoardVO selectNext(BoardVO board) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".selectNext", board);
	}
	
	@Override
	public BoardVO selectPrev(BoardVO board) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".selectPrev", board);
	}
	
	@Override
	public List<BoardCommentVO> selectCommentList(BoardCommentVO comment) throws Exception {
		return sqlSessionTemplate.selectList(NAMESPACE + ".selectCommentList", comment);
	}
	
	@Override
	public void insertComment(BoardCommentVO comment) throws Exception {
		sqlSessionTemplate.insert(NAMESPACE + ".selectCommentList", comment);
	}
	
	@Override
	public void updateComment(BoardCommentVO comment) throws Exception {
		sqlSessionTemplate.update(NAMESPACE + ".selectCommentList", comment);
	}
	
	@Override
	public void deleteComment(int commentNo) throws Exception {
		sqlSessionTemplate.delete(NAMESPACE + ".selectCommentList", commentNo);
	}
	
	public void insertBoard(BoardVO board) throws Exception {
		sqlSessionTemplate.insert(NAMESPACE + ".insertBoard", board);
	}
	
	@Override
	public int selectBoardNo(BoardVO board) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".selectBoardNo", board);
	}

	public void insertBoardFile(BoardFileVO file) throws Exception {
		sqlSessionTemplate.insert(NAMESPACE + ".insertBoardFile", file);
	}

	public void deleteBoard(String data) throws Exception { 
		sqlSessionTemplate.delete(NAMESPACE + ".deleteBoard", data);
	}
	
	public void deleteFileByBoardNo(String data) throws Exception {
		sqlSessionTemplate.delete(NAMESPACE + ".deleteFileByBoardNo", data);
	}
	
	public void deleteCommentByBoardNo(String data) throws Exception {
		sqlSessionTemplate.delete(NAMESPACE + ".deleteCommentByBoardNo", data);
	}
	
	@Override
	public BoardFileVO selectBoardFile(BoardVO board) throws Exception {
		return sqlSessionTemplate.selectOne(NAMESPACE + ".selectBoardFile", board);
	}
	
	@Override
	public void insertViewCount(BoardVO board) throws Exception {
		sqlSessionTemplate.insert(NAMESPACE + ".insertViewCount", board);
	}
	
	
	
}
