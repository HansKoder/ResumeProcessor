package org.hans.demo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ResumeProcessor {

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
