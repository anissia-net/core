package anissia.domain.anime

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "anime_genre")
class AnimeGenre (
    @Id
    @Column("genre")
    var genre: String = ""
) {

}

/*
CREATE TABLE `anime_genre` (
  `genre` varchar(100) NOT NULL,
  PRIMARY KEY (`genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
