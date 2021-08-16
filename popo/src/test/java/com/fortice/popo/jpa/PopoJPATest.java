package com.fortice.popo.jpa;

import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.dao.PopoDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PopoJPATest {

    @Autowired
    PopoDAO popoDAO;

    @Test
    public void getListTest(){
        //given
        List<Popo> popoList = popoDAO.findAll();

        //when
        boolean status = popoList.isEmpty();

        //then
        Assertions.assertEquals(false, status);
        Assertions.assertEquals(1, popoList.get(0).getId());
        Assertions.assertEquals( 1, (int) popoList.get(0).getCategory());
    }

    public void getPopoByIdTest(){
        Optional<Popo> popo = popoDAO.findById(1L);
        Byte one = 1, zero = 0;
        Popo mockPopo = new Popo(1L, 1L, one, one, zero, one, "/image/background.png");

        Assertions.assertEquals(mockPopo, popo);
    }
}
