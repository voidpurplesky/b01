/*
   // 주소 코드 1 전국 2 서울 전체 25 강남

   주소 상세 String address
회사명 company
잡명 jobname
잡상세 jobdetail
업무
    // 필수스킬 techStack
    // 우대스킬
    Spring Boot
    // 마감일 endDate
    // 수습기간 0 없음 1m 2m 3m ?
    // 경력 조건 1 1~5 경력 조건 _이상 _~_ 신입 신입~2년차
    // 링크 list
    // 링크종류 1이상 잡코/사람인 code list??

Hibernate:
    create table job (
        jno bigint not null auto_increment,
        address varchar(500) not null,
        career1 tinyint,
        career2 tinyint,
        company varchar(500) not null,
        detail varchar(2000),
        end_date datetime(6),
        name varchar(500),
        probation tinyint,
        tech_stack varchar(500),
        teck_stack_list varbinary(255),
        primary key (jno)
    ) engine=InnoDB


    INSERT INTO job
	(address,
	career1,
	career2,
	company,
	detail,
	end_date,
	name,
	probation,
	tech_stack)
	VALUES (
	'서울',
	0,
	0,
	'와그',
	'detail',
	'2024-08-31T23:59:59',
	'백엔드 개발자 모집(PHP, java)',
	3,
	'Java|Spring Boot'
	);
 */
package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jno;

    // 주소 코드
    // 주소 상세

    // 회사명
    @Column(length = 500, nullable = false)
    String company;
    // 잡명
    @Column(length = 500)
    String name;
    // 업무
    @Column(length = 2000)
    String detail;
    // 스킬
    @Column(length = 500)
    String techStack;

    //List<String> teckStackList;
    // 우대
    // 마감일
    LocalDateTime endDate;
    // 수습기간 x개월
    Byte probation;
    // 경력 조건 _이상 _~_ 신입 신입~2년차
    Byte career1;
    Byte career2;
    @Column(length = 500, nullable = false)
    String address;
    // 링크
    String link;
    // 링크종류 1이상 잡코/사람인
    //String site;
}
