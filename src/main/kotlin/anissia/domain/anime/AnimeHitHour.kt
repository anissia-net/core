package anissia.domain.anime

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable


@Table(name = "anime_hit_hour")
@IdClass(AnimeHitHour.Key::class)
class AnimeHitHour (
    @Id
    @Column
    var hour: Long = 0,

    @Id
    @Column
    var animeNo: Long = 0,

    @Column
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
