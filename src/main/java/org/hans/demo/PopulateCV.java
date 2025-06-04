package org.hans.demo;

import java.io.File;
import java.util.Objects;

public class PopulateCV {

    public static void main(String[] args) {
        File[] files = ResumeUtil.extractResumes("cvs-original/", ".pdf");

        if (Objects.isNull(files))
            throw new IllegalArgumentException("Must have cvs");

        for (File file : files){
            ResumeUtil.cloneResumes(file, 249);
        }
    }

}
