package com.fortice.popo.global.util;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class FileUtil {
    private static String rootPath;

    public static String uploadFile(MultipartFile file, String resource, int identifier) throws Exception{
        if(file.isEmpty())
            return "";

        Checker.checkFileType(file);

        String path = Formatter.getPathWithResourceAndFile(resource, new Date(), identifier, file.getOriginalFilename());
        System.out.println(rootPath + path);
        File dest = new File(rootPath + path);
        file.transferTo(dest);

        return path;
    }

    public static void deleteFile(String path) {
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

    public FileUtil(String rootPath) {
        this.rootPath = rootPath;
    }
}
