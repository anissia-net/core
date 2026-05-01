package anissia.domain.agenda.repository

import anissia.domain.agenda.AgendaPoll
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AgendaPollRepository : CoroutineCrudRepository<AgendaPoll, Long> { //, QuerydslPredicateExecutor<AgendaPoll> {


}
