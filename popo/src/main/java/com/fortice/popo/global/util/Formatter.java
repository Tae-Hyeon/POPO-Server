package com.fortice.popo.global.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@NoArgsConstructor
public class Formatter {
    public String DateFileNameFormat(Data today){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;
        String dateString =dateFormat.format(dateFormat.format(today));
        return dateString;
    }
}
