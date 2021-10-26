package com.fortice.popo.global.util;

import com.fortice.popo.global.common.GlobalValue;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class FileUtil {
    public static String uploadFile(MultipartFile file, String resource, int identifier) throws Exception{
        if(file.isEmpty())
            return "";
        Checker.checkFileType(file);

        String path = Formatter.getPathWithResourceAndFile(resource, new Date(), identifier, file.getOriginalFilename());
        File dest = new File(GlobalValue.getRootPath() + path);
        file.transferTo(dest);

        return path;
    }

    public static void deleteFile(String path) {
        File deleteFile = new File(GlobalValue.getRootPath() + path);

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
