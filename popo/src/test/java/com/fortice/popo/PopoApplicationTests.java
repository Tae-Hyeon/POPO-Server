package com.fortice.popo;

import com.fortice.popo.jpa.PopoRepositoryTest;
import com.fortice.popo.jpa.TrackerRepositoryTest;
import com.fortice.popo.rest.PopoAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@WebAppConfiguration
class PopoApplicationTests {
	PopoRepositoryTest popoJPATest;
	PopoAPITest popoAPITest;
	TrackerRepositoryTest trackerDAOTest;
	@Test
	void contextLoads() throws Exception {
		trackerDAOTest.getDayTest();
//		popoJPATest.getListTest();
//		popoJPATest.getPopoByIdTest();
//		popoAPITest.getListPopoTest();
	}
}
