package kr.or.ddit.borad.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardService {

	public ServiceResult insertBoard(BoardVO boardVO);

	public BoardVO selectBoard(int boNo);

	public ServiceResult updateBoard(BoardVO boardVO);

	public ServiceResult deleteBoard(int boNo);

	// 일반적인 방법의 목록 조회
	public List<BoardVO> selectBoardList();

	// 페이징을 이용한 목록 조회
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);
	
}
