package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

    private Long jno;
    private String company;
    private String name;
    private String detail;
    String techStack;
    LocalDateTime endDate;
    Byte probation;
    Byte career1;
    Byte career2;
    String address;
    String link;
}
