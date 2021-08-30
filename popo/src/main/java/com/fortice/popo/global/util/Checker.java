package com.fortice.popo.global.util;

import lombok.NoArgsConstructor;

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
