/*
5.4 게시물 관리 완성하기 - 서비스 계층과 DTO의 구현 - CRUD 작업 처리
 */
package org.zerock.b01.service;

import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO); // 등록 p462
    BoardDTO readOne(Long bno);         // 조회 p465
    void modify(BoardDTO boardDTO);     // 수정 p466
    void remove(Long bno);              // 삭제 p467
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
