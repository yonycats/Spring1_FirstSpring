package kr.or.ddit.notice.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeModifyController {
	
	@Inject
	private INoticeService noticeService;
	
	// 공지사항 게시판 수정 폼 페이지
	// 목적지 주소 uri : /update.do
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String noticeUpdateForm(int noticeNo, Model model) {
		NoticeVO noticeVO = noticeService.selectNotice(noticeNo);
		model.addAttribute("notice", noticeVO);
		model.addAttribute("status", "u");
		return "notice/form";
	}
	
	// 공지사항 게시판 수정
	// 목적지 주소 uri : /update.do
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String noticeUpdate(NoticeVO noticeVO, Model model) {
		String goPage = "";
		ServiceResult result = noticeService.updateNotice(noticeVO);
		
		if (result == ServiceResult.OK) {
			goPage = "redirect:/notice/detail.do?noticeNo=" + noticeVO.getNoticeNo();
		} else {
			model.addAttribute("notice", noticeVO);
			model.addAttribute("failed", "서버에러, 다시 시도해주세요!");
			model.addAttribute("status", "u");
			goPage = "notice/form";
		}
		return goPage;
	}
	
	// 공지사항 게시판 삭제
	// 목적지 주소 uri : /delete.do
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String noticeDelete(int noticeNo, Model model) {
		String goPage = "";
		ServiceResult result = noticeService.deleteNotice(noticeNo);
		
		if (result == ServiceResult.OK) {
			goPage = "redirect:/notice/list.do";
		} else {
			goPage = "redirect:/notice/detail.do?noticeNo=" + noticeNo;
		}
		
		return goPage;
	}
}
