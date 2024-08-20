/*
컨트롤러와 Swagger UI 테스트 p598
 */
package org.zerock.b01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.upload.UploadFileDTO;

@RestController
@Log4j2
public class UpDownController {

    @Value("${org.zerock.upload.path}") // import 시에 springframework로 시작하는 Value
    private String uploadPath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);

        if (uploadFileDTO.getFiles() != null) {
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                log.info(multipartFile.getOriginalFilename());
            });
        }
        return null;
    }
}
