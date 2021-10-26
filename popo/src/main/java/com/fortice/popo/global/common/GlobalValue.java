package com.fortice.popo.global.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalValue {
    private static String rootPath;
    private static String imageServerURI;

    public static String getRootPath() {
        return rootPath;
    }

    @Value("${path.root}")
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public static String getImageServerURI() {
        return imageServerURI;
    }

    @Value("${uri.image-server}")
    public void setImageServerURI(String imageServerURI) { this.imageServerURI = imageServerURI; }
}
