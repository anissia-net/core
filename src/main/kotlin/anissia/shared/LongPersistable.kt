package anissia.shared

import org.springframework.data.domain.Persistable

abstract class LongPersistable: Persistable<Long> {
    override fun isNew(): Boolean = id == 0L
}
