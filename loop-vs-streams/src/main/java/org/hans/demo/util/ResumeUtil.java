package org.hans.demo.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.hans.demo.shared.RoleProfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public class ResumeUtil {

    public static void cloneResumes(File originalResume, int copies) {
        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i < copies; i++) {
            File dest = new File("loop-vs-streams/cvs/cv-" + i + "-" + uuid + ".pdf");
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

    public static String extractText(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document).toLowerCase();
        } catch (IOException e) {
            System.out.println("[ERR] Extract Text is impossible, detail " + e.getMessage());
            return "";
        }
    }


}
