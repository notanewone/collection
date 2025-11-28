import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@Tag(TestGroup.INTEGRATION_TEST)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {RealTestConfig.class},
        loader = AnnotationConfigContextLoader.class
)
public abstract class NewIntegrationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

}