package com.sunny.blog.board;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping({"", "/"})
    public String list(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "board/list";
    }

    @GetMapping("/post")
    public String post() {
        return "board/post";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }


    @GetMapping("/list")
    public String boardsForm(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

//    @PostMapping("/board")
//    public String boardAdd(BoardDto boardDto){
//    Board board = new Board(boardDto.getTitle(), boardDto.getContent(), boardDto.getWriter());
//    boardRepository.save(board);
//    return "redirect:/";
//    }


}
