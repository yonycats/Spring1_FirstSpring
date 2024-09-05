package kr.or.ddit.main.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.borad.service.IBoardService;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
public class MainController {
	
	@Inject
	private IBoardService boardService;
	@Inject
	private IFreeService freeService;
	@Inject
	private INoticeService noticeService;
	
	// 요청 url가 2개 이상일 때	
	@RequestMapping(value = {"/", "/main.do"}, method = RequestMethod.GET)
	public String main(Model model) {
		// 일반게시판, 자유게시판, 공지사항 게시판 총 3개의 게시판 데이터를
		// 메인화면에 각 게시판 영역에 최신글 5개를 출력해주세요.
		
		// 그리고, 각 게시판 영역별 총 게시글 수도 함께 표시해주세요.
		// 메인 화면을 브라우저에 띄워보면 어떤 영역에 어떤 데이터를 넣어야 할지가 보여집니다.(참고)
		PaginationInfoVO<BoardVO> pagingVOBoard = new PaginationInfoVO<BoardVO>(10,5);
		int totalRecord1 = boardService.selectBoardCount(pagingVOBoard);
		pagingVOBoard.setTotalRecord(totalRecord1);
		model.addAttribute("pagingVOBoard", pagingVOBoard);
		
		List<BoardVO> boardList = boardService.selectBoardList();
		model.addAttribute("boardList", boardList);
		
		
		PaginationInfoVO<FreeVO> pagingVOFree = new PaginationInfoVO<FreeVO>(10,5);
		int totalRecord2 = freeService.selectFreeCount(pagingVOFree);
		pagingVOFree.setTotalRecord(totalRecord2);
		model.addAttribute("pagingVOFree", pagingVOFree);
		
		List<FreeVO> freeList = freeService.selectFreeList();
		model.addAttribute("freeList", freeList);
		
		
		PaginationInfoVO<NoticeVO> pagingVONotice = new PaginationInfoVO<NoticeVO>(10,5);
		int totalRecord3 = noticeService.selectNoticeCount(pagingVONotice);
		pagingVONotice.setTotalRecord(totalRecord3);
		model.addAttribute("pagingVONotice", pagingVONotice);
		
		List<NoticeVO> noticeList = noticeService.selectNoticeList();
		model.addAttribute("noticeList", noticeList);
		
		return "main";
	}
}
