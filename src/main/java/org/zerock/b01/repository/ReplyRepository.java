package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.b01.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 특정 게시물의 댓글 조회와 인덱스 p537
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable);
}
