package anissia.domain.anime

import org.springframework.data.relational.core.mapping.Column
import java.io.Serializable

data class AnimeHitHourId(
    @Column("hour")
    var hour: Long = 0,
    @Column("anime_no")
    var animeNo: Long = 0
) : Serializable
