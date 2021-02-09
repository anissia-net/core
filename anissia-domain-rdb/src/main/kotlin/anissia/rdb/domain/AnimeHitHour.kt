package anissia.rdb.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(
        uniqueConstraints = [UniqueConstraint(name = "anime_hit_hour_pk1", columnNames = ["hour", "animeNo"])],
        indexes = [Index(name = "anime_hit_hour_idx1", columnList = "animeNo,hour")]
)
@IdClass(AnimeHitHour.Key::class)
data class AnimeHitHour (
        @Id
        @Column(nullable = false, length = 10)
        var hour: Long = 0,

        @Id
        @Column(nullable = false)
        var animeNo: Long = 0,

        @Column(nullable = false)
        var hit: Long = 0
) {
        val key get() = Key(hour, animeNo)

        data class Key(var hour: Long = 0, var animeNo: Long = 0) : Serializable
}

/*
CREATE TABLE `anime_hit_hour` (
  `hour` bigint(20) NOT NULL,
  `anime_no` bigint(20) NOT NULL,
  `hit` bigint(20) NOT NULL,
  PRIMARY KEY (`anime_no`,`hour`),
  KEY `anime_hit_hour_idx1` (`anime_no`,`hour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
