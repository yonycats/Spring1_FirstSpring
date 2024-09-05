package kr.or.ddit.notice.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;
	
	// 공지사항 게시판 등록 폼 페이지
	// 목적지 주소 uri : /form.do
	@RequestMapping("/form.do")
	public String noticeForm() {
		return "notice/form";
	}
		
	// 공지사항 게시판 등록
	// 목적지 주소 uri : /insert.do
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String noticeInsert(NoticeVO noticeVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		
		if (StringUtils.isBlank(noticeVO.getNoticeTitle())) {
			errors.put("noticeTitle", "제목을 입력해주세요.");
		}
		if (StringUtils.isBlank(noticeVO.getNoticeContent())) {
			errors.put("noticeContent", "내용을 입력해주세요.");
		}
		
		if (errors.size() > 0) {
			model.addAttribute("notice", noticeVO);
			model.addAttribute("errors", errors);
			goPage = "notice/form";
		} else {
			noticeVO.setNoticeWriter("notice001");
		}
		
		ServiceResult result = noticeService.insertNotice(noticeVO);
		
		if (result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/detail.do?noticeNo=" + noticeVO.getNoticeNo();
		} else {
			model.addAttribute("notice", noticeVO);
			model.addAttribute("failed", "서버에러, 다시 시도해주세요.");
			goPage = "notice/form";
		}
		return goPage;
	}
}
