package com.fortice.popo.domain.popo.application;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.model.User;
import com.fortice.popo.domain.popo.dao.OptionDAO;
import com.fortice.popo.domain.popo.dao.PopoDAO;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import com.fortice.popo.domain.popo.dto.PopoDTO;
import com.fortice.popo.global.common.response.Response;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.util.Checker;
import com.fortice.popo.global.util.FileUtil;
import com.fortice.popo.global.util.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PopoCrudService {
    @Autowired
    private PopoDAO popoDAO;
    @Autowired
    private OptionDAO OptionDAO;

    private Checker checker = new Checker();
    private Formatter formatter = new Formatter();
    private FileUtil fileUtil = new FileUtil();

    @Value("${path.root}")
    private String rootPath;
    @Value("${uri.image-server}")
    private String imageServerURI;

    private Response returnResponse(int code, String message, Object data) throws Exception{
        Response<Object> response = new Response<>(code, message, data);
        return response;
    }

    public Response getPopoList() throws Exception{
        List<PopoDTO> popoList = popoDAO.findPoposByUserId(1);
        for(PopoDTO popo : popoList)
            popo.setUri(imageServerURI);

        return returnResponse(200, "포포 리스트 조회 성공", popoList);
    }

    public Response getPopo(Integer popoId) throws Exception{
        Popo popo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkPermission(popo, 1);

        return returnResponse(200, "포포 조회 성공", popo);
    }

    public Response setDefaultPopo(List<MultipartFile> backgrounds) throws Exception{
        List<Popo> newPopos = new ArrayList<>();
        List<PopoDTO> popoResponse = new ArrayList<>();

        for(int i = 1; i <= 12; i++)
        {
            Popo popo = Popo.builder()
                    .conceptId(1)
                    .background(fileUtil.uploadFile(backgrounds.get(i - 1), "popo", i))
                    .category(-1)
                    .order(i)
                    .user(User.builder().id(1).build())
                    .build();

            popoResponse.add(new PopoDTO(popo, imageServerURI));
            newPopos.add(popo);
        }

        popoDAO.saveAll(newPopos);

        return returnResponse(200, "포포 생성 성공", popoResponse);
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

    public Response changeBackground(Integer popoId, MultipartFile background) throws Exception {
        Popo popo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);
        checker.checkPermission(popo, 1);

        String preImagePath = popo.getTracker_image();
        String path = fileUtil.uploadFile(background, "tracker", 0);
        popo.setTracker_image(path);
        popoDAO.save(popo);
        fileUtil.deleteFile(preImagePath);

        return returnResponse(200, "포포 배경 수정 성공", imageServerURI + path);
    }
}
