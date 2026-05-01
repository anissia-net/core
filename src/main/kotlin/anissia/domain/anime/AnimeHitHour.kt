package anissia.domain.anime

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table(name = "anime_hit_hour")
class AnimeHitHour (
    @Id
    var id: AnimeHitHourId = AnimeHitHourId(),

    @Column("hit")
    var hit: Long = 0
) {
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
