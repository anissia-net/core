package anissia.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tools.jackson.databind.DeserializationFeature
import tools.jackson.databind.ObjectMapper
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinFeature
import tools.jackson.module.kotlin.KotlinModule

@Configuration
class JacksonConfiguration {
    // kotlin에서 jackson 사용시 object 만들기에 부족한 값이 있더라도 null이 아닌 기본값을 쓴다.
    @Bean
    fun objectMapper(): ObjectMapper {
        return JsonMapper.builder()
            .addModule(
                KotlinModule.Builder()
                    .configure(KotlinFeature.NullIsSameAsDefault, true)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build()
            )
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)
            .build()
    }
}
