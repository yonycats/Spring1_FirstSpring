package kr.or.ddit.free.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;

@Controller
@RequestMapping("/free")
public class FreeModifyController {
	
	@Inject
	private IFreeService freeService;
	
	// 자유 게시판 수정 폼 페이지
	// 목적지 주소 uri : /update.do
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String freeUpdateForm(int freeNo, Model model) {
		FreeVO freeVO = freeService.selectFree(freeNo);
		model.addAttribute("free", freeVO);
		model.addAttribute("status", "u");
		return "free/form";
	}
	
	// 자유 게시판 수정
	// 목적지 주소 uri : /update.do
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String freeUpdate(FreeVO freeVO, Model model) {
		String goPage = "";
		ServiceResult result = freeService.updateFree(freeVO);
		
		if (result == ServiceResult.OK) {
			goPage = "redirect:/free/detail.do?freeNo=" + freeVO.getFreeNo();
		} else {
			model.addAttribute("free", freeVO);
			model.addAttribute("failed", "서버에러, 다시 시도해주세요!");
			model.addAttribute("status", "u");
			goPage = "free/form";
		}
		return goPage;
	}
	
	// 자유 게시판 삭제
	// 목적지 주소 uri : /delete.do
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String freeDelete(int freeNo, Model model) {
		String goPage = "";
		ServiceResult result = freeService.deleteFree(freeNo);
		
		if (result == ServiceResult.OK) {
			goPage = "redirect:/free/list.do";
		} else {
			goPage = "redirect:/free/detail.do?freeNo=" + freeNo;
		}
		
		return goPage;
	}
}
