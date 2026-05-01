package anissia.domain.activePanel

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table
class ActivePanel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var apNo: Long = 0,

    @Column
    var published: Boolean = false,

    @Column
    var code: String = "",

    @Column
    var status: String = "",

    @Column
    var an: Long = 0,

    @Lob
    @Column
    var data1: String? = null,

    @Lob
    @Column
    var data2: String? = null,

    @Lob
    @Column
    var data3: String? = null,

    @Column
    var regDt: OffsetDateTime = OffsetDateTime.now()
) {

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
