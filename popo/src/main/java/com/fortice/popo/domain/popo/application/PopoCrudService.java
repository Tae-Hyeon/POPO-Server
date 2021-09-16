package com.fortice.popo.domain.popo.application;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.model.User;
import com.fortice.popo.domain.popo.dao.OptionDAO;
import com.fortice.popo.domain.popo.dao.PopoDAO;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import com.fortice.popo.global.common.response.Response;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.util.Checker;
import com.fortice.popo.global.util.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PopoCrudService {
    @Autowired
    PopoDAO popoDAO;
    @Autowired
    OptionDAO OptionDAO;

    Checker checker = new Checker();
    Formatter formatter = new Formatter();

    @Value("${path.local.root}")
    String rootPath;

    private Response returnResponse(int code, String message, Object data) throws Exception{
        Response<Object> response = new Response<>(code, message, data);
        return response;
    }

    public Response getPopoList() throws Exception{
        List<Popo> popoList = popoDAO.findPoposByUser(1);

        return returnResponse(200, "포포 리스트 조회 성공", popoList);
    }

    public Response getPopo(Integer popoId) throws Exception{
        Popo popo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkPermission(popo, 1);

        return returnResponse(200, "포포 조회 성공", popo);
    }

    public Response setDefaultPopo(List<MultipartFile> backgrounds) throws Exception{
        checker.checkFileType(backgrounds);
        List<Popo> newPopos = new ArrayList<>();
        System.out.println("root path : " + rootPath);
        for(int i = 1; i <= 12; i++)
        {
            MultipartFile background = backgrounds.get(i - 1);
            String path = formatter.getPathWithResourceAndFile("popo", new Date(), i, background.getOriginalFilename());
            File dest = new File(rootPath + path);
            background.transferTo(dest);

            Popo popo = Popo.builder()
                    .conceptId(1)
                    .background(path)
                    .category(-1)
                    .order(i)
                    .user(User.builder().id(1).build())
                    .build();

            newPopos.add(popo);
        }

        popoDAO.saveAll(newPopos);

        return returnResponse(200, "포포 생성 성공", newPopos);
    }

    public Response insertPopo(Integer popoId, PopoCreateRequest request) throws Exception{
        Popo newPopo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkPermission(newPopo, 1);

        newPopo.setCategory(request.getCategory());
        newPopo = popoDAO.save(newPopo);
        if(!request.isOptionEmpty()) {
            List<Option> newOptions = request.getOptions(newPopo);
            OptionDAO.saveAll(newOptions);
        }

        return returnResponse(200, "포포 생성 성공", newPopo);
    }

    public Response deletePopo(Integer popoId) throws Exception{
        Popo popo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkPermission(popo, 1);
        popoDAO.deleteById(popoId);

        popo.setCategory(-1);
        popoDAO.save(popo);

        return returnResponse(200, "포포 삭제 성공", null);
    }

    public Response changeBackground(Integer popoId, String background) throws Exception {
        Popo popo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkPermission(popo, 1);

        popo.setBackground(background);
        popoDAO.save(popo);

        return returnResponse(200, "포포 배경 수정 성공", null);
    }
}
