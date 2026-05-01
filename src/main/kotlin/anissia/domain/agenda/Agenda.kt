package anissia.domain.agenda

import anissia.shared.LongPersistable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table("agenda")
class Agenda (
    @Id
    @Column("agenda_no")
    var agendaNo: Long = 0,

    @Column("code")
    var code: String = "",

    @Column("status")
    var status: String = "",

    @Column("an")
    var an: Long = 0,

    @Column("data1")
    var data1: String? = null,

    @Column("data2")
    var data2: String? = null,

    @Column("data3")
    var data3: String? = null,

    @Column("reg_dt")
    var regDt: OffsetDateTime = OffsetDateTime.now(),

    @Column("upd_dt")
    var updDt: OffsetDateTime = OffsetDateTime.now(),
): LongPersistable() {
    override fun getId(): Long = agendaNo
}

/*
CREATE TABLE `agenda` (
  `agenda_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `status` varchar(32) NOT NULL,
  `an` bigint(20) NOT NULL,
  `data1` longtext DEFAULT NULL,
  `data2` longtext DEFAULT NULL,
  `data3` longtext DEFAULT NULL,
  `reg_dt` datetime NOT NULL,
  `upd_dt` datetime NOT NULL,
  PRIMARY KEY (`agenda_no`),
  KEY `agenda_idx1` (`code`,`status`,`agenda_no`),
  KEY `agenda_idx2` (`code`,`status`,`an`,`agenda_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
