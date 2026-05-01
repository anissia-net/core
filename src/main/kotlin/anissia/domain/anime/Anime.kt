package anissia.domain.anime

import anissia.shared.LongPersistable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column
import java.time.OffsetDateTime


@Table(name = "anime")
class Anime (
    @Id
    @Column("anime_no")
    var animeNo: Long = 0,

    @Column("status")
    var status: AnimeStatus = AnimeStatus.ON,

    /**
     * +++ week +++
     * 0 | 日 | 일요일 | Sunday
     * 1 | 月 | 월요일 | Monday
     * 3 | 火 | 화요일 | Tuesday
     * 4 | 水 | 수요일 | Wednesday
     * 5 | 木 | 목요일 | Thursday
     * 6 | 金 | 금요일 | Friday
     * 7 | 土 | 토요일 | Saturday
     * +++ week exception +++
     * 8 | 外 | 기타 | Other
     * 9 | 新 | 신작 | New
     */
    @Column("week")
    var week: String = "",

    @Column("time")
    var time: String = "",

    @Column("subject")
    var subject: String = "",

    @Column("original_subject")
    var originalSubject: String = "",

    @Column("autocorrect")
    var autocorrect: String = "",

    @Column("genres")
    var genres: String = "",

    @Column("start_date")
    var startDate: String = "",

    @Column("end_date")
    var endDate: String = "",

    @Column("website")
    var website: String = "",

    @Column("x")
    var x: String = "",

    @Column("note")
    var note: String = "",

    @Column("caption_count")
    var captionCount: Int = 0,

    @Column("upd_dt")
    var updDt: OffsetDateTime = OffsetDateTime.now(),

): LongPersistable() {
    override fun getId(): Long = animeNo
}

/*
-- PRIMARY KEY (`originalSubject`), 추가해야함

CREATE TABLE `anime` (
  `anime_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) NOT NULL,
  `week` varchar(1) NOT NULL,
  `time` varchar(5) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `original_subject` varchar(100) NOT NULL,
  `autocorrect` varchar(512) NOT NULL,
  `genres` varchar(64) NOT NULL,
  `start_date` varchar(10) NOT NULL,
  `end_date` varchar(10) NOT NULL,
  `website` varchar(128) NOT NULL,
  `x` varchar(128) NOT NULL,
  `note` varchar(512) NOT NULL,
  `caption_count` int(11) NOT NULL,
  `upd_dt` datetime NOT NULL,
  PRIMARY KEY (`anime_no`),
  UNIQUE KEY `anime_uk1` (`subject`),
  KEY `anime_idx1` (`status`,`week`,`time`),
  KEY `anime_idx2` (`status`,`anime_no`),
  KEY `anime_idx3` (`autocorrect`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
