package kr.or.ddit.borad.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardDAO {

	public int insertBoard(BoardVO boardVO);

	public void incrementHit(int boNo);

	public BoardVO selectBoard(int boNo);

	public int updateBoard(BoardVO boardVO);

	public int deleteBoard(int boNo);

	// 일반적인 방법의 목록 조회
	public List<BoardVO> selectBoardList();

	// 페이징을 이용한 목록 조회
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);

}
