package org.hans.demo;

import org.hans.demo.model.RoleProfile;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CandidateStream {

    public List<Candidate> getCandidates (List<File> files) {
         return files.stream()
                .map(file -> {
                    String text = ResumeProcessor.extractText(file);
                    Map<RoleProfile, Integer> scores = ResumeScorer.scoreResume(text);
                    return getBestMatch(file.getName(), scores);
                }).toList();

    }

    private Candidate getBestMatch (String fileName, Map<RoleProfile, Integer> scores) {
        RoleProfile bestRole = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
        int bestScore = scores.get(bestRole);

        return new Candidate(fileName, bestRole, bestScore);
    }

}
