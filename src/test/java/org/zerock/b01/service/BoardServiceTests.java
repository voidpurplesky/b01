/*
5.4 게시물 관리 완성하기 - 서비스 계층과 DTO의 구현 - CRUD 작업 처리 - 등록 작업 처리 p464
 */
package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.BoardDTO;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    // 등록 p464
    @Test
    public void register() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);
        log.info("bno:" + bno);
    }

    // 수정 p467
    @Test
    public void modify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(3L)
                .title("MODIFY TITLE")
                .content("MODIFY CONTENT")
                .build();
        boardService.modify(boardDTO);
    }

    // 삭제
}
