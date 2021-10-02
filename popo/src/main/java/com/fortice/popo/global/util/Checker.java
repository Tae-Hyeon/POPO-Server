package com.fortice.popo.global.util;

import com.fortice.popo.domain.model.Day;
import com.fortice.popo.domain.model.OptionContent;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.global.error.exception.MultipartFileTypeRestrictException;
import com.fortice.popo.global.error.exception.NoPermissionException;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class Checker {
    public boolean checkOwner(int ownerId, int requestId) {
        if(ownerId == requestId)
            return true;
        return false;
    }

    public String checkDateForm(int date) {
        if(date > 9)
            return Integer.toString(date);
        return "0" + Integer.toString(date);
    }

    public void checkEmpty(Object data) throws Exception{
        if(data == null)
            throw new NotFoundDataException();
    }

    public void checkPermission(Popo popo, int userId) throws Exception{
        if(!checkOwner(popo.getOwnerId(), userId))
            throw new NoPermissionException();
    }

    public void checkPermission(Day day, int userId) throws Exception{
        if(!checkOwner(day.getOwnerId(), userId))
            throw new NoPermissionException();
    }

    public void checkPermission(OptionContent contents, int userId) throws Exception{
        if(!checkOwner(contents.getOwnerId(), userId))
            throw new NoPermissionException();
    }

    public boolean checkFileType(MultipartFile file) throws Exception{
        String mimeType = file.getContentType();
        if(!mimeType.split("/")[0].equals("image"))
            throw new MultipartFileTypeRestrictException();

        return true;
    }

    public boolean checkFileType(List<MultipartFile> files) throws Exception{
        for(MultipartFile file : files) {
            String mimeType = file.getContentType();
            if (!mimeType.split("/")[0].equals("image"))
                throw new MultipartFileTypeRestrictException();
        }

        return true;
    }

//    public boolean validDate(String date, int type) {
//        if(type == 0) //year
//        {
//            if(date.isBlank()) return false;
//            if(date.length() != 4) return false;
//            if(Integer.parseInt(date) < 2000 && Integer.parseInt(date) > 2022) return false;
//        }
//        else(type == 1)
//        {
//            if(date.isBlank()) return false;
//            if(date.length() != 2) return false;
//            if(Integer.parseInt(date) < 0 && Integer.parseInt(date) > 31) return false;
//        }
//        return true;
//    }
}
