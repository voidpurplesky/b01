package org.zerock.b01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.b01.dto.ReplyDTO;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {

    /*

    http://localhost:8081/replies/

     http://localhost:8081/replies 404
     */
    @GetMapping("/")
    public String index() {
        return "hello reply";
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(@RequestBody ReplyDTO replyDTO) {
        log.info(replyDTO);
        Map<String, Long> resultMap = Map.of("rno", 111L);
        return ResponseEntity.ok(resultMap);
    }
}
