package anissia.domain.anime

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table(
    indexes = [Index(name = "anime_hit_idx__hour_animeNo_ip", columnList = "hour,animeNo,ip")]
)
class AnimeHit (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long = 0,

    @Column
    var ip: String = "",

    @Column
    var animeNo: Long = 0,

    @Column
    var hour: Long = 0
) {

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
