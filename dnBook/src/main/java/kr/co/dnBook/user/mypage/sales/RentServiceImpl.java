package kr.co.dnBook.user.mypage.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dnBook.mapper.UserRentMapper;
import kr.co.dnBook.vo.RentVO;

@Service
public class RentServiceImpl implements RentService{
	@Autowired
	UserRentMapper dao;

	@Override
	public Map<String, Object> rentList(String id) throws Exception {
		List<RentVO> list = dao.selectRent(id);
		
		// 전체 게시글 카운트
		int totalCount = dao.selectRentCount(id);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("totalCount", totalCount);
		return result;
	}
}
