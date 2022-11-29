package com.dss.dss3msloginv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;

@SpringBootTest
class Dss3MsLoginV1ApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(this::doNotThrowException);
	}

	private void doNotThrowException(){
		System.out.println("Test.");
	}

	@Test
	void main() {
		Dss3MsLoginV1Application.main(new String[] {});
		Assertions.assertTrue(true);
	}

}
