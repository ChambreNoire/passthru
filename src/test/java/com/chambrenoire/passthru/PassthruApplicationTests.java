package com.chambrenoire.passthru;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = {
    "--gateway.url=http://test.com/",
})
class PassthruApplicationTests {

    @Test
    void contextLoads() {
    }
}
