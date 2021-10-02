package com.fortice.popo.global.util;

import com.fortice.popo.domain.model.Day;
import com.fortice.popo.domain.model.Popo;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class Formatter {

    Checker checker = new Checker();

    public String getPathWithResourceAndFile(String resource, Date date, int middle, String filename) {
        if(middle == 0)
            middle = (int)(Math.random() * 100) + 13;
        return "/" + resource + "/" + getDateFileNameFormat(date) + "-" + middle + "-" + filename;
    }
    public String getDateFileNameFormat(Date today){
        return Long.toString(today.getTime());
    }

    public String getDateFormatByYearAndMonth(String year, String month){
        LocalDate now = LocalDate.now();

        String y = year.isBlank() ? checker.checkDateForm(now.getYear()) : year;
        String m = month.isBlank() ? checker.checkDateForm(now.getMonthValue()) : month;

        return y + "-" + m;
    }
}
