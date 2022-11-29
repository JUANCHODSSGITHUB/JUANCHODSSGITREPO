package com.dss.dss5msactorv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Dss5MsActorV1ApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(this::doNotThrowException);
    }

    private void doNotThrowException(){
        System.out.println("Test.");
    }

    @Test
    void main() {
        Dss5MsActorV1Application.main(new String[] {});
        Assertions.assertTrue(true);
    }

}
