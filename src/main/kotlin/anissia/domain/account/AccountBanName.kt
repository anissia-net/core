package anissia.domain.account

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table(name = "account_ban_name")
class AccountBanName (
    @Id
    @Column
    var name: String = ""
) {

}

/*
CREATE TABLE `account_ban_name` (
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
*/
