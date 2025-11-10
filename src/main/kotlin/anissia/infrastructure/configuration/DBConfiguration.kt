package anissia.infrastructure.configuration

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["anissia.domain.*"])
@EntityScan(basePackages = ["anissia.domain.*"])
class DBConfiguration
