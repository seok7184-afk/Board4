package com.green.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {

    private final UserController userController;
	
	@Autowired
	private  MenuMapper   menuMapper;
	
	@Autowired
	private  BoardMapper  boardMapper;


    BoardController(UserController userController) {
        this.userController = userController;
    }
	
	// /Board/List?menu_id=MENU01
	@RequestMapping("/List")
	public  ModelAndView   list( MenuDTO  menuDto  ) {
		
		// 메뉴전체목록 조회 - menus.jsp
		List<MenuDTO>  menuList  =  menuMapper.getMenuList();
		log.info("menuList:" + menuList);
				
		// 게시물 목록 조회 - list.jsp (menu_id=MENU01)
		List<BoardDto>  boardList  =  boardMapper.getBoardList( menuDto );		
		log.error("boardList:" + boardList);
		
		// 넘어온 menu_id 
		String menu_id = menuDto.getMenu_id();
		
		ModelAndView  mv  =  new  ModelAndView();
		mv.setViewName("board/list");
		mv.addObject("menuList",  menuList);
		mv.addObject("bList", boardList);
		mv.addObject("menu_id", menu_id);
		return  mv;
	}
	
	// /Board/View?idx=1
	@RequestMapping("/view")
	public ModelAndView view(BoardDto boardDto) {
		ModelAndView mv = new ModelAndView();
		
		// 메뉴 목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// idx 글의 조회수를 1 증가
		boardMapper.incHit(boardDto);
		
		// idx 로 조회한 게시글
		BoardDto board = boardMapper.getBoard(boardDto);
		
		board.setContent(board.getContent().replace("\n", "<br>"));
		
		
		
		mv.setViewName("board/view");
		mv.addObject("menuList", menuList);
		mv.addObject("board", board);
		return mv;
	}
	
	
	
	// /Board/WriteForm
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(BoardDto boardDto) {
		
		// 메뉴목록
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		System.out.println("/Board/WriteForm boardDto:" + boardDto);
		
		String menu_id = boardDto.getMenu_id();
		String menu_name = menuMapper.getMenuName(menu_id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/write");
		mv.addObject("menu_id", menu_id);
		mv.addObject("menu_name", menu_name);
		mv.addObject("menuList", menuList);
		return mv;
	}
	
	// /Board/Write?menu_id=MENU01&title=&content=&writer=a
	@RequestMapping("/Write")
	public ModelAndView write(BoardDto boardDto) {
		// db 저장
		boardMapper.insertBoard(boardDto);
		
		String menu_id = boardDto.getMenu_id();
		
		// 페이지 이동
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		// mv.addObject("", mv);
		return mv;
	}
	
	// /Board/UpdateForm?idx=5?menu_id=
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm(BoardDto boardDto) {
		ModelAndView mv = new ModelAndView();
		
		BoardDto board = boardMapper.getBoard(boardDto);
		
		mv.addObject("board", board);
		mv.setViewName("board/update");
		
		return mv;
	}
	
	// /Board/Update
	@RequestMapping("Update")
	public ModelAndView update(BoardDto boardDto) {
		
		boardMapper.updateBoard(boardDto);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/view?idx=" + boardDto.getIdx());
		
		return mv;
	}
	
	// /Board/Delete?idx=${board.idx}&menu_id=${menu_id}
	@RequestMapping("/Delete")
	public ModelAndView delete(BoardDto boardDto) {

	    // DB 삭제
	    boardMapper.deleteBoard(boardDto);

	    // 삭제 후 해당 메뉴 목록으로 이동
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("redirect:/Board/List?menu_id=" + boardDto.getMenu_id());

	    return mv;
	}
	
}











