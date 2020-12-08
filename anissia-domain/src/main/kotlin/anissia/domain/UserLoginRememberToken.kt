package anissia.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(
        uniqueConstraints = [UniqueConstraint(columnNames = ["tn"])],
        indexes = [Index(name = "user_login_remember_token__exp_dt", columnList = "expDt", unique = false)]
)
data class UserLoginRememberToken (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false)
        var tn: Long = 0,

        @Column(nullable = false, length = 512, unique = true)
        var token: String = "",

        @Column(nullable = false)
        var un: Long = 0,

        @Column(nullable = false)
        var expDt: LocalDateTime = LocalDateTime.now()
) {
        val absoluteToken: String get() = "${tn}-${token}"
}