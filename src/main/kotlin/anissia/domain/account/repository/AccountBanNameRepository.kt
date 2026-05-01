package anissia.domain.account.repository

import anissia.domain.account.AccountBanName
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AccountBanNameRepository : CoroutineCrudRepository<AccountBanName, String> { //, QuerydslPredicateExecutor<AccountBanName>
}
