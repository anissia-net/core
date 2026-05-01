package anissia.domain.session

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import me.saro.kit.TextKit
import java.time.OffsetDateTime


@Table(name = "login_token")
class LoginToken (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var tokenNo: Long = 0,

    @Column
    var token: String = "",

    @Column
    var an: Long = 0,

    @Column
    var expDt: OffsetDateTime
) {
    companion object {
        fun create(
            an: Long,
        ): LoginToken =
            LoginToken(
                token = TextKit.generateBase62(128, 512),
                an = an,
                expDt = OffsetDateTime.now().plusDays(10)
            )
    }

    val absoluteToken: String get() = "${tokenNo}-${token}"
}

/*
CREATE TABLE `login_token` (
  `token_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(512) NOT NULL,
  `an` bigint(20) NOT NULL,
  `exp_dt` datetime NOT NULL,
  PRIMARY KEY (`token_no`),
  UNIQUE KEY `login_token_uk1` (`token`),
  KEY `login_token_idx1` (`exp_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
