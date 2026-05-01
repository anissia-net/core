package anissia.domain.store

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table
class Store (
    @Id
    @Column
    var code: String = "", // code

    @Column
    var cv: String = "", // code value : simple value

    @Lob
    @Column
    var data: String = "" // long value
) {

}

/*
CREATE TABLE `store` (
  `code` varchar(64) NOT NULL,
  `cv` varchar(128) DEFAULT NULL,
  `data` longtext DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
