package com.fortice.popo.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortice.popo.domain.tracker.repository.TrackerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrackerRepositoryTest {

    @Autowired
    TrackerRepository trackerRepository;

    @Test
    public void getDayTest() throws Exception{
        //given
        ObjectMapper objectMapper = new ObjectMapper();

        ///Optional<DayResponse> day = trackerDAO.getDayResponseById(1);
        //String json = objectMapper.writeValueAsString(day);
        //when
        //System.out.println(json)    ;
    }
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        e.printStackTrace();
    }
}
