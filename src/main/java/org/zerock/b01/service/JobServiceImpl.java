package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Job;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.JobDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.repository.JobRepository;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final ModelMapper modelMapper;
    private final JobRepository jobRepository;

    @Override
    public Long register(JobDTO dto) {
        return 0L;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        return null;
    }

    @Override
    public void modify(JobDTO dto) {

    }

    @Override
    public void remove(Long bno) {

    }

    @Override
    public PageResponseDTO<JobDTO> list(PageRequestDTO pageRequestDTO) {
        Page<Job> result = jobRepository.findAll(pageRequestDTO.getPageable("jno"));
        List<JobDTO> dtoList = result.getContent().stream()
                .map(job -> modelMapper.map(job, JobDTO.class))
                .toList();

        return PageResponseDTO.<JobDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .total((int)result.getTotalElements())
                .build();
    }
}
