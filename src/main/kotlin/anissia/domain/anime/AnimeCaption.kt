package anissia.domain.anime

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.time.OffsetDateTime


@Table("anime_caption")
class AnimeCaption (
    @Id
    var id: AnimeCaptionId = AnimeCaptionId(),

    @Column("episode")
    var episode: String = "0",

    @Column("upd_dt")
    var updDt: OffsetDateTime = OffsetDateTime.now(),

    @Column("website")
    var website: String = "",
) {
    data class Key(val anime: Long = 0, val an: Long = 0) : Serializable

    fun edit(episode: String, updDt: OffsetDateTime, website: String) {
        this.episode = episode
        this.updDt = updDt
        this.website = website
    }
}

/*
CREATE TABLE `anime_caption` (
  `anime_no` bigint(20) NOT NULL,
  `an` bigint(20) NOT NULL,
  `episode` varchar(10) NOT NULL,
  `upd_dt` datetime NOT NULL,
  `website` varchar(512) NOT NULL,
  PRIMARY KEY (`an`,`anime_no`),
  KEY `anime_caption_idx1` (`an`,`upd_dt`),
  KEY `anime_caption_idx2` (`upd_dt`),
  KEY `anime_caption_fk_idx1` (`anime_no`),
  CONSTRAINT `anime_caption_fk1` FOREIGN KEY (`anime_no`) REFERENCES `anime` (`anime_no`),
  CONSTRAINT `anime_caption_fk2` FOREIGN KEY (`an`) REFERENCES `account` (`an`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
