/*
Hibernate:
    create table board_image (
        uuid varchar(255) not null,
        file_name varchar(255),
        ord integer not null,
        board_bno bigint,
        primary key (uuid)
    ) engine=InnoDB
Hibernate:
    alter table if exists board_image
       add constraint FKo4dbcmbib7vwlk8eplv2cwbe2
       foreign key (board_bno)
       references board (bno)
 */
package org.zerock.b01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage> {
    @Id
    private String uuid;

    private String fileName;

    private int ord;

    @ManyToOne
    private Board board;

    @Override
    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }

    public void changeBoard(Board board) {
        this.board = board;
    }
}
