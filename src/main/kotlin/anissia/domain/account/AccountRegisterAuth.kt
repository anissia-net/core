package anissia.domain.account

import anissia.shared.LongPersistable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table(name = "account_recover_auth")
class AccountRegisterAuth (
    @Id
    @Column("no")
    var no: Long = 0,

    @Column("token")
    var token: String = "",

    @Column("email")
    var email: String = "",

    @Column("ip")
    var ip: String = "",

    @Column("data")
    var data: String = "",

    @Column("exp_dt")
    var expDt: OffsetDateTime = OffsetDateTime.now(),

    @Column("used_dt")
    var usedDt: OffsetDateTime? = null
): LongPersistable() {
    override fun getId(): Long = no
}

/*
CREATE TABLE `account_register_auth` (
  `no` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(512) NOT NULL,
  `email` varchar(64) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `data` varchar(255) NOT NULL,
  `exp_dt` datetime NOT NULL,
  `used_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`no`),
  UNIQUE KEY `account_register_auth_uk2` (`token`),
  KEY `account_register_auth_idx1` (`email`,`exp_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
