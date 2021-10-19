package com.fortice.popo.domain.dummy.service;

import com.fortice.popo.domain.dummy.dto.DummyRequest;
import com.fortice.popo.domain.model.Day;
import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.OptionContent;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.repository.OptionRepository;
import com.fortice.popo.domain.tracker.repository.TrackerContentRepository;
import com.fortice.popo.domain.tracker.repository.TrackerRepository;
import com.fortice.popo.global.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class DummyService {
    private HashMap<String, Integer> categoryValueMap;

    private OptionRepository optionRepository;
    private TrackerRepository trackerRepository;
    private TrackerContentRepository trackerContentRepository;

//    public Object setDummyFromOption(String category, DummyRequest request) {
//        System.out.println("test");
//    }

    public Object setDummyFromDay(String category, DummyRequest request) throws Exception {
        String preTime[] = request.getMonth().split("-");
        System.out.println(preTime[0] +'-'+ preTime[1]);
        int maxDay = LocalDate.of(Integer.parseInt(preTime[0]), Integer.parseInt(preTime[1]), 1).lengthOfMonth();
        int length = 3;
        Popo popo = Popo.builder().id(request.getPopoId()).build();
        List<Integer> lengthOfOption = List.of(4, 3, 3, 3, 2, 3, 3, 4);
        List<Option> options = optionRepository.getOptionByPopo(request.getPopoId());
        FileUtil fileUtil = new FileUtil("C:/Users/hanth/OneDrive/Desktop/POPO-Server/popo/image");

        for(int i = 1; i <= maxDay; i++)
        {
            int rand = (int) (Math.random() * request.getFiles().size());
            String path = FileUtil.uploadFile(request.getFiles().get(rand),"day",0);
            Date date = new Date(Integer.parseInt(preTime[0]) - 1900, Integer.parseInt(preTime[1]) - 1, i);
            System.out.println(Integer.parseInt(preTime[0]) + ' ' + Integer.parseInt(preTime[1]));
            Day day = Day.builder()
                    .date(date)
                    .image(path)
                    .popo(popo)
                    .build();
            day = trackerRepository.save(day);

            List<OptionContent> contents = new ArrayList<>();
            for(int j = 0; j < length; j++) {
                String str = "";
                if(j == 0 || j == 1) str = request.getFiles().get(rand).getOriginalFilename().split("\\.")[0];
                else if(j == 1) {
                    int rand2 = (int) (Math.random() * 3);
                    if(rand2 == 0) str = "";
                    else if(rand2 == 1) str = "학교 근처";
                    else if(rand2 == 2) str = "집에서 해먹기~";
                }
                else if(j == 2) {
                    int rand2 = (int) (Math.random() * 5);
                    if(rand2 == 0) str = "1";
                    else if(rand2 == 1) str = "2";
                    else if(rand2 == 2) str = "3";
                    else if(rand2 == 3) str = "4";
                    else if(rand2 == 4) str = "5";
                }
                else if(j == 3) {
                    int rand2 = (int) (Math.random() * 5);
                    if(rand2 == 0) str = "가볍게 읽기 좋은 책이다. 쉬는 시간에 아무것도 안한다면 한번씩 읽으면 좋을 것 같다.";
                    else if(rand2 == 1) str = "흥미로운 내용이 많다.";
                    else if(rand2 == 2) str = "집에서 편하게 보기 좋을 것 같다. 굳이 영화관까지 가서 볼 영화는 아니다.";
                    else if(rand2 == 3) str = "감동적이었다. 끝나고 너무 여운이 남아 울어버렸다 ㅜㅜㅜ 남들은 내 감정을 이해 못할수도 ?";
                    else if(rand2 == 4) str = "역시 남들이 많이 보는 영화는 다르다." + request.getFiles().get(rand).getOriginalFilename().split("\\.")[0] + "의 명성은 어디 가지 않는다.";
                }
                OptionContent content = OptionContent.builder()
                        .day(day)
                        .option(options.get(j))
                        .contents(str)
                        .build();

                contents.add(content);
            }
            trackerContentRepository.saveAll(contents);
        }
        return "test";
    }

    @Autowired
    public void setTrackerDAO(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
    }

    @Autowired
    public void setOptionDAO(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Autowired
    public void setTrackerContentDAO(TrackerContentRepository trackerContentRepository) {
        this.trackerContentRepository = trackerContentRepository;
    }
}
