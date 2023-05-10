package com.example.likelion3.entity;

import com.example.likelion3.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO dto){
        BoardEntity entity = new BoardEntity();
        entity.setBoardWriter(dto.getBoardWriter());
        entity.setBoardPass(dto.getBoardPass());
        entity.setBoardTitle(dto.getBoardTitle());
        entity.setBoardContents(dto.getBoardContents());
        entity.setBoardHits(0);
        return entity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        return boardEntity;

    }
}
