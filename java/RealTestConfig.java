import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@TestConfiguration
@PropertySource({
        "classpath:application-test.properties"
})
@ComponentScan({
        "com.test.client",
        "com.test.transformer"
})
@Import({ AnotherConfig.class })
@EnableTransactionManagement
@EnableAutoConfiguration
public class RealTestConfig extends RealConfig {

}