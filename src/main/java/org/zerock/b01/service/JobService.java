package org.zerock.b01.service;

import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.JobDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

public interface JobService {
    Long register(JobDTO dto); // 등록 p462
    BoardDTO readOne(Long bno);         // 조회 p465
    void modify(JobDTO dto);     // 수정 p466
    void remove(Long bno);              // 삭제 p467
    PageResponseDTO<JobDTO> list(PageRequestDTO pageRequestDTO);
}
