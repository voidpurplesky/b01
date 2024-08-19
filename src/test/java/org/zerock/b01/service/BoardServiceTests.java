/*
5.4 게시물 관리 완성하기 - 서비스 계층과 DTO의 구현 - CRUD 작업 처리 - 등록 작업 처리 p464
 */
package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

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
        log.info("bno:{}", bno);
    }

    @Test
    public void read() {
        BoardDTO boardDTO = boardService.readOne(407L);
        log.info(boardDTO);
    }
    // 수정 p467
    @Test
    public void modify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(407L)
                .title("MODIFY TITLE")
                .content("MODIFY CONTENT")
                .build();
        boardService.modify(boardDTO);

        boardDTO = boardService.readOne(407L);
        log.info(boardDTO);
    }

    // 삭제

    @Test
    public void ceil() {
        int total = 101;
        int size = 10;
        log.info(Math.ceil((total/(double)size))); // 10.1 > 11.0 올림
    }

    @Test
    public void list() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info(responseDTO);
    }
    //PageResponseDTO(page=1, size=10, total=403, start=1, end=10, prev=false, next=true, dtoList=[BoardDTO(bno=407,


}
