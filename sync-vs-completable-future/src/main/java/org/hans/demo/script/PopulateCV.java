package org.hans.demo.script;

import org.hans.demo.global.Global;
import org.hans.demo.util.ResumeUtil;

import java.io.File;
import java.util.Objects;

public class PopulateCV {

    public static void main(String[] args) {
        File[] files = ResumeUtil.extractResumes(Global.DIR_CVS_ORIGINAL, Global.PDF);

        if (Objects.isNull(files))
            throw new IllegalArgumentException("Must have cvs");

        for (File file : files){
            ResumeUtil.cloneResumes(file, 249);
        }
    }

}
