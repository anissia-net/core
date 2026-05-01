package anissia.domain.account


import anissia.shared.LongPersistable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime


@Table(name = "account")
class Account (
    @Id
    @Column("an")
    var an: Long = 0, // account number

    @Column("email")
    var email: String = "",

    @Column("password")
    var password: String = "",

    @Column("name")
    var name: String = "",

    @Column("reg_dt")
    var regDt: OffsetDateTime = OffsetDateTime.now(),

    @Column("last_login_dt")
    var lastLoginDt: OffsetDateTime = OffsetDateTime.now(),

    @Column("ban_expire_dt")
    var banExpireDt: OffsetDateTime? = null,

    @Column("roles")
    var roles: String = "",
): LongPersistable() {
    override fun getId(): Long = an
    val isBan: Boolean get() = banExpireDt?.isAfter(OffsetDateTime.now()) == true
    val roleStringList: List<String> get() = roles.splitToSequence(",").filter { it.isNotEmpty() }.toList()
    val roleList: List<AccountRole> get() = roles.splitToSequence(",").filter { it.isNotEmpty() }.map { AccountRole.valueOf(it) }.toList()
    val isAdmin: Boolean get() = roleList.any { it == AccountRole.TRANSLATOR || it == AccountRole.ROOT }
    val isTranslator: Boolean get() = roleList.any { it == AccountRole.TRANSLATOR }
}

/*
CREATE TABLE `account` (
`an` bigint(20) NOT NULL AUTO_INCREMENT,
`email` varchar(64) NOT NULL,
`password` varchar(512) NOT NULL,
`name` varchar(16) NOT NULL,
`reg_dt` datetime NOT NULL,
`last_login_dt` datetime NOT NULL,
`ban_expire_dt` datetime DEFAULT NULL,
`roles` varchar(60) NOT NULL,
PRIMARY KEY (`an`),
UNIQUE KEY `account_pk2` (`email`),
UNIQUE KEY `account_pk3` (`name`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

*/
