package anissia.domain.anime

import org.springframework.data.relational.core.mapping.Column
import java.io.Serializable

data class AnimeCaptionId(
    @Column("anime")
    val anime: Long = 0,
    @Column("an")
    val an: Long = 0
) : Serializable
