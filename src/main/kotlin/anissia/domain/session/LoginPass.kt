package anissia.domain.session

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table(
    indexes = [
        Index(name = "login_pass_idx__passDt", columnList = "passDt"),
        Index(name = "login_pass_idx__an", columnList = "an")
    ]
)
class LoginPass (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var loginPassNo: Long = 0,

    @Column
    var an: Long = 0,

    @Column
    var connType: String = "",

    @Column
    var ip: String = "",

    @Column
    var passDt: OffsetDateTime = OffsetDateTime.now()
) {
    companion object {
        fun create(
            an: Long,
            connType: String,
            ip: String
        ): LoginPass =
            LoginPass(
                an = an,
                connType = connType,
                ip = ip
            )
    }
}

/*
CREATE TABLE `login_pass` (
  `login_pass_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `an` bigint(20) NOT NULL,
  `conn_type` varchar(10) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `pass_dt` datetime NOT NULL,
  PRIMARY KEY (`login_pass_no`),
  KEY `login_pass_idx1` (`pass_dt`),
  KEY `login_pass_idx2` (`an`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
