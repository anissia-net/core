package anissia.domain.account.repository

import anissia.domain.account.Account
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AccountRepository : CoroutineCrudRepository<Account, Long> {
    suspend fun findByEmail(email: String): Account?

    suspend fun findByEmailAndName(email: String, name: String): Account?

    suspend fun findByName(name: String): Account?

    suspend fun existsByName(name: String): Boolean

    suspend fun existsByEmail(mail: String): Boolean
}
