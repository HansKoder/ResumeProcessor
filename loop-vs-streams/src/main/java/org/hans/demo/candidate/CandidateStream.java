package org.hans.demo.candidate;

import org.hans.demo.model.Candidate;
import org.hans.demo.model.RoleProfile;
import org.hans.demo.util.ResumeUtil;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CandidateStream {

    public List<Candidate> getCandidates (List<File> files) {
         return files.stream()
                .map(file -> {
                    String text = ResumeUtil.extractText(file);
                    Map<RoleProfile, Integer> scores = ResumeUtil.scoreResume(text);
                    return getBestMatch(file.getName(), scores);
                }).toList();

    }

    private Candidate getBestMatch (String fileName, Map<RoleProfile, Integer> scores) {
        RoleProfile bestRole = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
        int bestScore = scores.get(bestRole);

        return new Candidate(fileName, bestRole, bestScore);
    }

}
