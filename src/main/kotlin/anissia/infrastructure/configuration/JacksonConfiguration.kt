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
    /**
     * jackson 3.x(추정) 이후 역직렬화의 기본값이 변경되어 이전 값과 유사하게 동작하도록 설정
     *
     * 조건: 역직렬화시 맴버에 필요한 파라미터가 없는경우.
     * - jackson 구버전: 기본값 사용.
     * - jackson 신버전: null 사용. (해당 맴버가 nullable이 아닌 경우 코틀린 null 오류발생)
     * (신버전의 정책이 프로그래밍 원칙상 더 올바른 방향이긴 하나 현 프로젝트의 호환성과 잠재적 버그 때문에 구버전과 유사하게 수정.)
     */
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
