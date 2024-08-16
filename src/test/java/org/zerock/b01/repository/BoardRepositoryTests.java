/*
5.3 Spring Data JPA - JpaRespository 인터페이스
                    - 테스트 코드를 통한 CRUD/페이징 처리 확인 p438
build.gradle에
	testCompileOnly 'org.projectlombok:lombok'
	testAnnotationProcessor 'org.projectlombok:lombok'

	추가해야 로그 @Log4j2 사용 가능
 */
package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b01.domain.Board;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2 // org.projectlombok:lombok
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    // insert 기능 테스트 p439
    @Test
    public void insertOne() {
        Board board = Board.builder()
                .title("title...1")
                .content("content...1")
                .writer("user1")
                .build();
        Board result = boardRepository.save(board);
        log.info("BNO: " + result.getBno());
    }

    // select 기능 테스트 p440

    /*
Hibernate: select b1_0.bno,b1_0.content,b1_0.moddate,b1_0.regdate,b1_0.title,b1_0.writer from board b1_0 where b1_0.bno=?

Hibernate:
    select
        b1_0.bno,
        b1_0.content,
        b1_0.moddate,
        b1_0.regdate,
        b1_0.title,
        b1_0.writer
    from
        board b1_0
    where
        b1_0.bno=?

    SUCCESS
    o.z.b01.repository.BoardRepositoryTests  : Board(bno=2, title=title, content=content, writer=user2)
    FAIL
java.util.NoSuchElementException: No value present
     */
    @Test
    public void select() {
        Long bno = 3L;
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow(); // java.util.NoSuchElementException: No value present
        log.info(board);
    }

    // update 기능 테스트 p441
    @Test
    public void update() {
        Long bno = 2L;
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();
        board.change("title changed 2", "content changed 2");
        boardRepository.save(board);

    }

    // delete p442
    @Test
    public void delete() {
        Long bno = 2L;
        boardRepository.deleteById(bno);
    }

    // Pageable과 Page<E> 타입 p443
    @Test
    public void paging() {

        // page = 0 , page size = 10
        Pageable pageable
                = PageRequest.of(1, 20, Sort.by("bno").descending());
                                // pageNumber, pageSize, sort
        /*
        pageNumber - zero-based page number, must not be negative.
        pageSize - the size of the page to be returned, must be greater than 0.
        sort - must not be null, use Sort.unsorted() instead.

         */

        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count=" + result.getTotalElements()); // 402 total amount of elements.
        log.info("total pages=" + result.getTotalPages()); // 42        21      number of total page
        log.info("page number=" + result.getNumber()); // 0     1
        log.info("page size = " + result.getSize()); // 10      20
        List<Board> todoList = result.getContent();
        todoList.forEach(board -> log.info(board));
    }
}
