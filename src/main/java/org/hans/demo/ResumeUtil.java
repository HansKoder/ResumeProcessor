package org.hans.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ResumeUtil {

    public static void cloneResumes(File originalResume, int copies) {
        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i < copies; i++) {
            File dest = new File("cvs/cv-" + i + "-" + uuid + ".pdf");
            try {
                Files.copy(originalResume.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println("Error copiando CV: " + e.getMessage());
            }
        }
    }

    public static File[] extractResumes (String path, String suffix) {
        File folder = new File(path);
        return folder.listFiles((dir, name) -> name.endsWith(suffix));
    }
}
