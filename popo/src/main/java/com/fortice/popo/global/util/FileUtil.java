package com.fortice.popo.global.util;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class FileUtil {
    private Checker checker = new Checker();
    private Formatter formatter = new Formatter();

    @Value("${path.root}")
    private String rootPath;

    public String uploadFile(MultipartFile file, String resource, int identifier) throws Exception{
        if(file == null)
            return "";

        checker.checkFileType(file);

        String path = formatter.getPathWithResourceAndFile(resource, new Date(), identifier, file.getOriginalFilename());
        File dest = new File(rootPath + path);
        file.transferTo(dest);

        return path;
    }

    public void deleteFile(String path) {
        File deleteFile = new File(rootPath + path);

        if(deleteFile.exists() && deleteFile.isFile()) {
            if(deleteFile.delete())
                System.out.println(deleteFile.getPath() + "사진 삭제 성공");
            else{
                //TODO ERROR LOG
                System.out.println(deleteFile.getPath() + "사진 삭제 실패");
            }
        }
    }
}
