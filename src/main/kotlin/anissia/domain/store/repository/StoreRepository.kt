package anissia.domain.store.repository

import anissia.domain.store.Store
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface StoreRepository : CoroutineCrudRepository<Store, String> { //, QuerydslPredicateExecutor<AnimeStore>
}
