package com.fortice.popo;

import com.fortice.popo.jpa.PopoDAOTest;
import com.fortice.popo.jpa.TrackerDAOTest;
import com.fortice.popo.rest.PopoAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@WebAppConfiguration
class PopoApplicationTests {
	PopoDAOTest popoJPATest;
	PopoAPITest popoAPITest;
	TrackerDAOTest trackerDAOTest;
	@Test
	void contextLoads() throws Exception {
		trackerDAOTest.getDayTest();
//		popoJPATest.getListTest();
//		popoJPATest.getPopoByIdTest();
//		popoAPITest.getListPopoTest();
	}
}
