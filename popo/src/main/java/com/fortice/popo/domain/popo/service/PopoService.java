package com.fortice.popo.domain.popo.service;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.model.User;
import com.fortice.popo.domain.popo.repository.OptionRepository;
import com.fortice.popo.domain.popo.repository.PopoRepository;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import com.fortice.popo.domain.popo.dto.PopoDTO;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.util.Checker;
import com.fortice.popo.global.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PopoService {
    private final PopoRepository popoRepository;
    private final OptionRepository optionRepository;

    @Value("${uri.image-server}")
    private String imageServerURI;
    @Value("${path.root}")
    private String rootPath;

    public List<PopoDTO> getPopoList() throws Exception {
        List<PopoDTO> popoList = popoRepository.findPoposByUserId(1);
        for (PopoDTO popo : popoList)
            popo.setUri(imageServerURI);

        return popoList;
    }

    public Popo getPopo(Integer popoId) throws Exception {
        Popo popo = popoRepository.findById(popoId)
                .orElseThrow(NotFoundDataException::new);

        Checker.checkPermission(popo, 1);

        return popo;
    }

    public List<PopoDTO> setDefaultPopo(List<MultipartFile> backgrounds) throws Exception {
        FileUtil fileUtil = new FileUtil(rootPath);
        List<Popo> newPopos = new ArrayList<>();
        List<PopoDTO> popoResponse = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            Popo popo = Popo.builder()
                    //.conceptId(1)
                    .background(fileUtil.uploadFile(backgrounds.get(i - 1), "popo", i))
                    .category(-1)
                    .order(i)
                    .user(User.builder().id(1).build())
                    .build();

            //TODO: new
            popoResponse.add(new PopoDTO(popo, imageServerURI));
            newPopos.add(popo);
        }

        popoRepository.saveAll(newPopos);

        return popoResponse;
    }

    public PopoDTO insertPopo(Integer popoId, PopoCreateRequest request) throws Exception {
        Popo newPopo = popoRepository.findById(popoId)
                .orElseThrow(NotFoundDataException::new);

        Checker.checkPermission(newPopo, 1);

        newPopo.setCategory(request.getCategory());
        newPopo = popoRepository.save(newPopo);
        if (!request.isOptionEmpty()) {
            List<Option> newOptions = request.getOptions(newPopo);
            optionRepository.saveAll(newOptions);
        }
        //TODO: new
        return new PopoDTO(newPopo, imageServerURI);
    }

    public List<Option> getOptions(Integer popoId) throws Exception {
        List<Option> options = optionRepository.getOptionByPopo(popoId);

        Checker.checkPermission(options.get(0), 1);

        return options;
    }
//
//    public Object deletePopo(Integer popoId) throws Exception{
//        Popo popo = popoDAO.findById(popoId)
//                .orElseThrow(NotFoundDataException::new);
//
//        checker.checkPermission(popo, 1);
//        popoDAO.deleteById(popoId);
//
//        popo.setCategory(-1);
//        popoDAO.save(popo);
//
//        return null;
//    }

    public String changeBackground(Integer popoId, MultipartFile background) throws Exception {
        Popo popo = popoRepository.findById(popoId)
                .orElseThrow(NotFoundDataException::new);
        Checker.checkPermission(popo, 1);

        FileUtil fileUtil = new FileUtil(rootPath);
        String preImagePath = popo.getTracker_image();
        String path = fileUtil.uploadFile(background, "tracker", 0);
        popo.setTracker_image(path);
        popoRepository.save(popo);
        fileUtil.deleteFile(preImagePath);

        return imageServerURI + path;
    }
}
