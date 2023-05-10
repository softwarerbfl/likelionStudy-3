package com.example.likelion3.controller;

import com.example.likelion3.dto.BoardDTO;
import com.example.likelion3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    /**
     * 게시글 추가
     */
    // 글 작성 폼 띄우기
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    // 글 작성 버튼을 누르면 DB에 게시물 저장
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "index";
    }

    /**
     * 게시글 리스트 확인
     */
    // DB에 저장되어 있는 모든 게시물 불러오기
    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    /**
     * 게시글 조회
     */
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        boardService.updateHits(id);
        BoardDTO dto = boardService.findById(id);
        model.addAttribute("board", dto);

        return "detail";
    }

    /**
     * 게시글 삭제
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

    /**
     * 게시글 수정
     */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO dto = boardService.findById(id);
        model.addAttribute("boardUpdate", dto);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        //Entity를 찾아 수정하고 다시 DTO로 변환하여 가져오기
        BoardDTO dto = boardService.update(boardDTO);
        model.addAttribute("board", dto);
        return "detail";

    }
}
