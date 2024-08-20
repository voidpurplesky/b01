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
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.BoardImage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2 // org.projectlombok:lombok
//@Transactional
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
        Long bno = 411L;
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

    // 7장 파일 업로드 처리 - 7.2 @OneToMany p619
    @Test
    public void insertWithImages() {
        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for (int i = 0; i < 3; i++) {
            board.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");
        }
        boardRepository.save(board);
    }
    /*
Hibernate:
    insert
    into
        board
        (content, moddate, regdate, title, writer)
    values
        (?, ?, ?, ?, ?)
    returning bno
Hibernate:
    insert
    into
        board_image
        (board_bno, file_name, ord, uuid)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        board_image
        (board_bno, file_name, ord, uuid)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        board_image
        (board_bno, file_name, ord, uuid)
    values
        (?, ?, ?, ?)

     */

    @Test
    public void addImages() {

        Long bno = 410L;
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();
        board.addImage(UUID.randomUUID().toString(), "file.jpg");
        boardRepository.save(board);



    }

    @Test
    public void insertWithImages2() {
        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();


        board.addImage(UUID.randomUUID().toString(), "file.jpg");

        boardRepository.save(board);
    }

    @Test
    public void readWithImages() {
        Optional<Board> result = boardRepository.findByIdWithImages(414L);
        Board board = result.orElseThrow();
        log.info(board);
        log.info("-----------------------");
        for (BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);
        }
/*
Hibernate:
    select
        b1_0.bno,
        b1_0.content,
        is1_0.board_bno,
        is1_0.uuid,
        is1_0.file_name,
        is1_0.ord,
        b1_0.moddate,
        b1_0.regdate,
        b1_0.title,
        b1_0.writer
    from
        board b1_0
    left join
        board_image is1_0
            on b1_0.bno=is1_0.board_bno
    where
        b1_0.bno=?

Board(bno=414, title=Image Test, content=첨부파일 테스트, writer=tester,
imageSet=[
    BoardImage(uuid=67b2622d-eb86-455d-9919-0fbdee4a2d4a, fileName=file1.jpg, ord=1),
    BoardImage(uuid=f3cc5401-16d5-45b0-a9bb-007049feae99, fileName=file0.jpg, ord=0),
    BoardImage(uuid=f6f862b2-e131-4649-b375-4ff3c5f910dc, fileName=file2.jpg, ord=2)])

2024-08-20T17:50:31.757+09:00  INFO 11432 --- [b01] [           main] o.z.b01.repository.BoardRepositoryTests  : -----------------------
2024-08-20T17:50:31.758+09:00  INFO 11432 --- [b01] [           main] o.z.b01.repository.BoardRepositoryTests  : BoardImage(uuid=67b2622d-eb86-455d-9919-0fbdee4a2d4a, fileName=file1.jpg, ord=1)
2024-08-20T17:50:31.758+09:00  INFO 11432 --- [b01] [           main] o.z.b01.repository.BoardRepositoryTests  : BoardImage(uuid=f3cc5401-16d5-45b0-a9bb-007049feae99, fileName=file0.jpg, ord=0)
2024-08-20T17:50:31.758+09:00  INFO 11432 --- [b01] [           main] o.z.b01.repository.BoardRepositoryTests  : BoardImage(uuid=f6f862b2-e131-4649-b375-4ff3c5f910dc, fileName=file2.jpg, ord=2)
 */
    }
}
