package kosta.mvc.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.domain.FreeBoard;
import kosta.mvc.service.FreeBoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final FreeBoardService service;
	
	/**
	 * 전체검색
	 * */
	@RequestMapping("/list")
	public void list(Model model,@RequestParam(defaultValue = "0")int nowPage){
		//List<FreeBoard> list = service.selectAll();
		//model.addAttribute("list",list);
		
		//page처리...
		Pageable pageable = PageRequest.of(nowPage, 10,Direction.ASC,"bno");
		Page<FreeBoard> pageList= service.selectAll(pageable);
		
		
		model.addAttribute("pageList",pageList);
	}
	
	/**
	 * 한개 검색
	 * 패스배리어블.. 까먹지말자...
	 * */
	@RequestMapping("/read/{bno}")
	public ModelAndView read(@PathVariable Long bno,String state) {
		boolean b = state==null?true:false;
		FreeBoard board  = service.selectBy(bno, b);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/read");
		mv.addObject("board",board);
		return mv;
				
	}
	
	/**
	 * 등록폼
	 * */
	@RequestMapping("/write")
	public void writeForm() {
	}
	
	/**
	 * 등록하기
	 * */
	@RequestMapping("/insert")
	public String insert(FreeBoard board) {
		//content에 스크립트 요소(태그)를 문자로 교체
		String content = board.getContent().replace("<", "&lt;");
		board.setContent(content);
		service.insert(board);
		return "redirect:/board/list";
	}
	
	/**
	 * 수정폼
	 * */
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(Long bno) {
		FreeBoard board = service.selectBy(bno, false);
		return new ModelAndView("board/update","board",board);
	}
	
	/**
	 * 수정하기
	 * */
	@RequestMapping("/update")
	public String update(FreeBoard board) {
		service.update(board);
		
		return "redirect:/board/read/"+board.getBno()+"?state=1";
	}
	
	/**
	 * 삭제하기
	 * */
	@RequestMapping("/delete")
	public String delete(Long bno, String password) {
		service.delete(bno,password);
		return "redirect:/board/list";
	}
	
	/**
	 * 예외처리
	 * */
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView error(Exception e) {
		return new ModelAndView("error/errorView","errMsg",e.getMessage());
	}
}
