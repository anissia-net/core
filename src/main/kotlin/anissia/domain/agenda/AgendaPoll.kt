package anissia.domain.agenda

import anissia.shared.LongPersistable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table("agenda_poll")
class AgendaPoll (
    @Id
    @Column("poll_no")
    var pollNo: Long = 0,

    @Column("vote_up")
    var voteUp: Int = 0,

    @Column("vote_down")
    var voteDown: Int = 0,

    @Column("name")
    var name: String = "",

    @Column("an")
    var an: Long = 0,

    @Column("comment")
    var comment: String = "",

    @Column("reg_dt")
    var regDt: OffsetDateTime = OffsetDateTime.now(),
): LongPersistable() {
    override fun getId(): Long = pollNo
    val vote get() = voteUp + voteDown
}

/*
CREATE TABLE `agenda_poll` (
  `poll_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `agenda_no` bigint(20) NOT NULL,
  `vote_up` int(11) NOT NULL,
  `vote_down` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `an` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `reg_dt` datetime NOT NULL,
  PRIMARY KEY (`poll_no`),
  KEY `agenda_poll_idx1` (`agenda_no`,`poll_no`),
  CONSTRAINT `agenda_poll_fk1` FOREIGN KEY (`agenda_no`) REFERENCES `agenda` (`agenda_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
