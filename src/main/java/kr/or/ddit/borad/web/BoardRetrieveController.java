package kr.or.ddit.borad.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.borad.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping(value = "/board")
public class BoardRetrieveController {

	@Inject
	private IBoardService boardService;
	
	// 일반 게시판 목록 페이지 요청
	// GET과 POST를 둘 다 받을 수 있도록 메서드 방식을 지움
	// list에서 searchForm 메소드 방식은 post로 받기 때문에 get과 post 둘 다 사용할 수 있는 메소드를 만들기 위함
	@RequestMapping(value = "/list.do")
	public String boardList(
			// list는 현재 페이지, 검색타입, 검색키워드를 보냈을 때 받아낼 수 있는 구조가 필요
			// currentPage : default로 1이 넘어옴
			// searchType : default로 제목으로 검색될 수 있게 만듦 
			// -> 타입을 설정하지 않고 넘겼더라도, 제목만으로 목록이 조회될 수 있도록 설정
			// searchWord : 내가 입력한 검색 키워드가 담겨서 들어옴
			// but, 총 3개의 데이터는 필수 요소는 아니다! (required = false)
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, 
			@RequestParam(required = false, defaultValue = "title") String searchType, 
			@RequestParam(required = false) String searchWord, // searchWord가 존재한다면 검색을 했다는 것을 알 수 있음
			Model model) {
		// [방법 1] - 일반적인 목록 조회
//		List<BoardVO> boardList = boardService.selectBoardList();
//		model.addAttribute("boardList", boardList);
		
		// [방법 2] - 페이징 및 검색이 적용된 목록 조회
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>(10,5);
		
		// 브라우저에서 검색한 검색 유형, 검색 키워드를 이용한 검색 처리
		// 검색 키워드가 있으면 검색을 한거고, 키워드가 없다면 검색을 하지 않음
		if (StringUtils.isNoneBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		// startRow, endRow, startPage, endPage가 결정
		pagingVO.setCurrentPage(currentPage);
		// 총 게시글 수를 얻어온다.
		int totalRecord = boardService.selectBoardCount(pagingVO);
		// 총 page를 결정
		pagingVO.setTotalRecord(totalRecord);
		// 각 페이지에 대한 screenSize만큼의 게시글 목록을 가져온다.
		List<BoardVO> dataList = boardService.selectBoardList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/list";
	}
	
	// 일반 게시판 상세보기 페이지 요청
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String boardDetail(int boNo, Model model) {
		BoardVO boardVO = boardService.selectBoard(boNo);
		model.addAttribute("board", boardVO);
		return "board/view";
	}
	
}
