package anissia.domain.activePanel

import anissia.shared.LongPersistable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table("active_panel")
class ActivePanel (
    @Id
    @Column("ap_no")
    var apNo: Long = 0,

    @Column("published")
    var published: Boolean = false,

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
    var regDt: OffsetDateTime = OffsetDateTime.now()
): LongPersistable() {
    override fun getId() = apNo
}

/*
CREATE TABLE `active_panel` (
  `ap_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `published` bit(1) NOT NULL,
  `code` varchar(100) NOT NULL,
  `status` varchar(32) NOT NULL,
  `an` bigint(20) NOT NULL,
  `data1` longtext DEFAULT NULL,
  `data2` longtext DEFAULT NULL,
  `data3` longtext DEFAULT NULL,
  `reg_dt` datetime NOT NULL,
  PRIMARY KEY (`ap_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
