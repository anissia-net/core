package anissia.domain.account

import anissia.shared.LongPersistable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table(name = "account_recover_auth")
class AccountRecoverAuth (
    @Id
    @Column
    var no: Long = 0,

    @Column
    var token: String = "",

    @Column
    var an: Long = 0,

    @Column
    var ip: String = "",

    @Column
    var expDt: OffsetDateTime = OffsetDateTime.now(),

    @Column
    var usedDt: OffsetDateTime? = null,
): LongPersistable() {
    override fun getId(): Long = no
}

/*
CREATE TABLE `account_recover_auth` (
  `no` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(512) NOT NULL,
  `an` bigint(20) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `exp_dt` datetime NOT NULL,
  `used_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`no`),
  UNIQUE KEY `account_recover_auth_uk1` (`token`),
  KEY `account_recover_auth_idx1` (`an`,`exp_dt`),
  CONSTRAINT `account_recover_auth_fk1` FOREIGN KEY (`an`) REFERENCES `account` (`an`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
*/
