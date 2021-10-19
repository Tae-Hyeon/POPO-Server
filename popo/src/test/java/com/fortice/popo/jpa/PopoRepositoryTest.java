package com.fortice.popo.jpa;

import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.repository.PopoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PopoRepositoryTest {

    @Autowired
    PopoRepository popoRepository;

    @Test
    public void getListTest(){
        //given
        List<Popo> popoList = popoRepository.findAll();

        //when
        boolean status = popoList.isEmpty();

        //then
        Assertions.assertEquals(false, status);
        Assertions.assertEquals(1, popoList.get(0).getId());
        Assertions.assertEquals( 1, (int) popoList.get(0).getCategory());
    }

    public void getPopoByIdTest(){
        Optional<Popo> popo = popoRepository.findById(1);
        Popo mockPopo = Popo.builder().id(1).build();

        //Assertions.assertEquals(mockPopo, popo);
    }
}
