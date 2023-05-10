package com.example.likelion3.dto;

import com.example.likelion3.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO dto = new BoardDTO();
        dto.setId(boardEntity.getId());
        dto.setBoardWriter(boardEntity.getBoardWriter());
        dto.setBoardPass(boardEntity.getBoardPass());
        dto.setBoardTitle(boardEntity.getBoardTitle());
        dto.setBoardContents(boardEntity.getBoardContents());
        dto.setBoardHits(boardEntity.getBoardHits());
        dto.setBoardCreatedTime(boardEntity.getCreatedTime());
        dto.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return dto;
    }
}
