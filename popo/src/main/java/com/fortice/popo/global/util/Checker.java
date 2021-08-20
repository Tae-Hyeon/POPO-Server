package com.fortice.popo.global.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Checker {
    public boolean checkOwner(int ownerId, int requestId) {
        if(ownerId == requestId)
            return true;
        return false;
    }
}
