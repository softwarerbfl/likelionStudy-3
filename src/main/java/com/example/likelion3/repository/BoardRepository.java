package com.example.likelion3.repository;

import com.example.likelion3.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying //업데이트 쿼리나 딜리트 쿼리를 사용할 때 사용해야하는 애노테이션
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id = :id")
    void updateHits(@Param("id") Long id);
}

