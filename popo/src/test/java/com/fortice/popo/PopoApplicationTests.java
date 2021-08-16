package com.fortice.popo;

import com.fortice.popo.jpa.PopoJPATest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
class PopoApplicationTests {

	@Test
	void contextLoads() {
		PopoJPATest jpaTest = new PopoJPATest();
		jpaTest.getListTest();
		jpaTest.getPopoByIdTest();

	}
}
