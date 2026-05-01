package anissia.domain.agenda

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table(
    indexes = [
        Index(name = "agenda_poll_idx__agendaNo_pollNo", columnList = "agendaNo,pollNo")
    ],
)
class AgendaPoll (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var pollNo: Long = 0,

    @Column
    var voteUp: Int = 0,

    @Column
    var voteDown: Int = 0,

    @Column
    var name: String = "",

    @Column
    var an: Long = 0,

    @Column
    var comment: String = "",

    @Column
    var regDt: OffsetDateTime = OffsetDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agendaNo", foreignKey = ForeignKey(name = "agenda_poll_fk_agenda"))
    var agenda: Agenda? = null
) {
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
