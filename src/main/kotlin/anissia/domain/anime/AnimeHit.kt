package anissia.domain.anime

import anissia.shared.LongPersistable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table("anime_hit")
class AnimeHit (
    @Id
    @Column("id")
    var id: Long = 0,

    @Column("ip")
    var ip: String = "",

    @Column("anime_no")
    var animeNo: Long = 0,

    @Column("hour")
    var hour: Long = 0
): LongPersistable() {
    override fun getId(): Long = id
}

/*
CREATE TABLE `anime_hit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(39) NOT NULL,
  `anime_no` bigint(20) NOT NULL,
  `hour` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `anime_hit_idx1` (`hour`,`anime_no`,`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
