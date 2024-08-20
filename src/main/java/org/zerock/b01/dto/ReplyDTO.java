/*
6.1 REST 방식의 서비스 - REST 방식의 댓글 처리 준비 - URL설계와 DTO 설계 p521
 */
package org.zerock.b01.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long rno;

    @NotNull
    private Long bno;

    @NotEmpty
    private String replyText;

    @NotEmpty
    private String replyer;

    private LocalDateTime regDate, modDate;
}
