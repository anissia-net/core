package anissia.domain.board

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Table
class BoardTicker (
    @Id
    @Column
    var ticker: String = "",

    @Column
    var name: String = "",

    @Column
    val writeTopicRoles: String = "",

    @Column
    val writePostRoles: String = "",

    @Column
    val phTopic: String = ""
) {

}

/*
CREATE TABLE `board_ticker` (
  `ticker` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `write_topic_roles` varchar(100) NOT NULL,
  `write_post_roles` varchar(100) NOT NULL,
  `ph_topic` varchar(1024) NOT NULL,
  PRIMARY KEY (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
