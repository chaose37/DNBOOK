package kr.co.dnBook.admin.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dnBook.mapper.AdminBoardMapper;
import kr.co.dnBook.vo.BoardCommentVO;
import kr.co.dnBook.vo.BoardFileVO;
import kr.co.dnBook.vo.BoardSearchVO;
import kr.co.dnBook.vo.BoardVO;
import kr.co.dnBook.vo.PageVO;

@Service
public class AdminBoardServiceImpl implements AdminBoardService {
	@Autowired
	AdminBoardMapper dao;
	
	public Map<String, Object> listBoard(BoardSearchVO boardSearch) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("list", dao.selectList(boardSearch));
		PageVO page = new PageVO(boardSearch.getPageNo(), dao.selectTotalCount(boardSearch));
		result.put("page", page);
		return result;
	}
	
	@Override
	public Map<String, Object> detailBoard(BoardVO board) throws Exception {
		Map<String, Object> result = new HashMap<>();
		dao.insertViewCount(board);
		board = dao.selectDetail(board.getBoardNo());
		BoardFileVO file = new BoardFileVO();
		if(board.getBoardType() == 1) {
			file = dao.selectBoardFile(board);
		}
		BoardVO prev = dao.selectPrev(board);
		BoardVO next = dao.selectNext(board);
		result.put("file", file);
		result.put("board", board);
		result.put("prev", prev);
		result.put("next", next);
		return result;
	}
	
	@Override
	public List<BoardCommentVO> commentList(BoardCommentVO comment) throws Exception {
		return dao.selectCommentList(comment);
	}
	
	@Override
	public List<BoardCommentVO> insertComment(BoardCommentVO comment) throws Exception {
		dao.insertComment(comment);
		return dao.selectCommentList(comment);
	}
	
	
	@Override
	public List<BoardCommentVO> updateComment(BoardCommentVO comment) throws Exception {
		dao.updateComment(comment);
		return dao.selectCommentList(comment);
	}
	
	@Override
	public List<BoardCommentVO> deleteComment(BoardCommentVO comment) throws Exception {
		dao.deleteComment(comment.getCommentNo());
		return dao.selectCommentList(comment);
	}

	@Override
	public void insertBoard(BoardVO board, BoardFileVO file) throws Exception {
		dao.insertBoard(board);
		
		int boardNo = dao.selectBoardNo(board);
//		board.setBoardNo(boardNo);
		
		if (file != null) {
			file.setBoardNo(boardNo);
			dao.insertBoardFile(file);
		}
		
	}

	@Override
	public void deleteBoard(String data) throws Exception {
		
		String[] delNo = data.split(",");
		
		for (String delno : delNo){
			// 게시물 삭제
			dao.deleteBoard(delno);
			// 파일 삭제	
			dao.deleteFileByBoardNo(delno);
			// 댓글 삭제
			dao.deleteCommentByBoardNo(delno);
		}
	}

	
	
	
	
}
