package com.example.likelion3.service;

import com.example.likelion3.dto.BoardDTO;
import com.example.likelion3.entity.BoardEntity;
import com.example.likelion3.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO dto){
        BoardEntity boardEntity = BoardEntity.toSaveEntity(dto);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll(){
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    public BoardDTO findById(Long id){
        Optional<BoardEntity> entity = boardRepository.findById(id);
        if (entity.isPresent()){
            BoardEntity boardEntity = entity.get();
            BoardDTO dto = BoardDTO.toBoardDTO(boardEntity);
            return dto;
        }
        else{
            return null;
        }
    }

    // 게시글의 조회수 증가 메소드
    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    //게시글 삭제
    public void delete(Long id){
        boardRepository.deleteById(id);
    }
    public BoardDTO update(BoardDTO dto){
        // Entity 수정
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(dto);
        boardRepository.save(boardEntity);
        return findById(dto.getId()); // Service내에서 생성한 메소드 재활용
    }
}
