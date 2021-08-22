package com.fortice.popo;

import com.fortice.popo.jpa.PopoJPATest;
import com.fortice.popo.rest.PopoAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@WebAppConfiguration
class PopoApplicationTests {
	PopoJPATest popoJPATest;
	PopoAPITest popoAPITest;
	@Test
	void contextLoads() throws Exception {
//		popoJPATest.getListTest();
//		popoJPATest.getPopoByIdTest();
//		popoAPITest.getListPopoTest();
	}
}
