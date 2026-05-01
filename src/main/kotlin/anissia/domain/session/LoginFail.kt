package anissia.domain.session

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table(
    indexes = [
        Index(name = "login_fail_idx__failDt", columnList = "failDt"),
        Index(name = "login_fail_idx__ip_email_failDt", columnList = "ip,email,failDt")
    ]
)
class LoginFail (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var fn: Long = 0,

    @Column
    var ip: String = "",

    @Column
    var email: String = "",

    @Column
    var failDt: OffsetDateTime = OffsetDateTime.now()
) {
    companion object {
        fun create(
            ip: String,
            email: String
        ): LoginFail =
            LoginFail(
                ip = ip,
                email = email
            )
    }
}

/*
CREATE TABLE `login_fail` (
  `fn` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(40) NOT NULL,
  `email` varchar(64) NOT NULL,
  `fail_dt` datetime NOT NULL,
  PRIMARY KEY (`fn`),
  KEY `login_fail_idx1` (`fail_dt`),
  KEY `login_fail_idx2` (`ip`,`email`,`fail_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
