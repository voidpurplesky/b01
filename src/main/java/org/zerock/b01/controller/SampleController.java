package org.zerock.b01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.JobDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.service.JobService;

@Controller
@Log4j2
@RequiredArgsConstructor
public class SampleController {
/*
    @GetMapping("/")
    public String index() {
    log.info("index");
        return "index";
    }

 */

    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }

    private final JobService jobService;

    @GetMapping("/job")
    public void job(PageRequestDTO pageRequestDTO, Model model) {
        log.info("job");
        PageResponseDTO<JobDTO> responseDTO = jobService.list(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/detail")
    public void detail() {
        log.info("detail");
    }
}
