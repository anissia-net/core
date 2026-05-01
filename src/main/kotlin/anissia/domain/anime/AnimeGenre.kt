package anissia.domain.anime

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Table
class AnimeGenre (
    @Id
    @Column
    var genre: String = ""
) {

}

/*
CREATE TABLE `anime_genre` (
  `genre` varchar(100) NOT NULL,
  PRIMARY KEY (`genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
