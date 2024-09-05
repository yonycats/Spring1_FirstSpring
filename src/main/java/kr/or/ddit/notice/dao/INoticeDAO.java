package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDAO {

	public int insertNotice(NoticeVO noticeVO);

	public void incrementHit(int noticeNo);

	public NoticeVO selectNotice(int noticeNo);

	public int updateNotice(NoticeVO noticeVO);

	public int deleteNotice(int noticeNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);

	public List<NoticeVO> selectNoticeList();

}
