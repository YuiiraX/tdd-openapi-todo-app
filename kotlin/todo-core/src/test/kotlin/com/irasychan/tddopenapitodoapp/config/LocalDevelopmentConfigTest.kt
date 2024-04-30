import com.irasychan.tddopenapitodoapp.config.LocalDevelopmentConfig
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.test.context.runner.ApplicationContextRunner

@DisplayName("LocalDevelopmentConfig class")
class LocalDevelopmentConfigTest {

    private val contextRunner = ApplicationContextRunner()
        .withUserConfiguration(LocalDevelopmentConfig::class.java)

    @Nested
    @DisplayName("httpExchangeRepository method")
    inner class HttpExchangeRepositoryMethod {

        @Test
        @DisplayName("should return InMemoryHttpExchangeRepository when profile is local")
        fun returnsInMemoryHttpExchangeRepositoryWhenProfileIsLocal() {
            contextRunner.withPropertyValues("spring.profiles.active=local").run { context ->
                assertTrue(context.getBean("httpExchangeRepository") is InMemoryHttpExchangeRepository)
            }
        }

        @Test
        @DisplayName("should not return InMemoryHttpExchangeRepository when profile is not local")
        fun doesNotReturnInMemoryHttpExchangeRepositoryWhenProfileIsNotLocal() {
            contextRunner.withPropertyValues("spring.profiles.active=prod").run { context ->
                assertFalse(context.containsBean("httpExchangeRepository"))
            }
        }
    }
}