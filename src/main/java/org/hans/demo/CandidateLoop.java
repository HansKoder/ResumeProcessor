package org.hans.demo;

import org.hans.demo.model.RoleProfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CandidateLoop {

    public List<Candidate> getCandidates (List<File> files) {
        List<Candidate> list = new ArrayList<>();
        for (File file: files) {
            String text = ResumeProcessor.extractText(file);
            Map<RoleProfile, Integer> scores = ResumeScorer.scoreResume(text);
            list.add(getBestMatch(file.getName(), scores));
        }

        return list;
    }

    private Candidate getBestMatch (String fileName, Map<RoleProfile, Integer> scores) {
        RoleProfile bestRole = null;
        int bestScore = Integer.MIN_VALUE;

        for (Map.Entry<RoleProfile, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > bestScore) {
                bestScore = entry.getValue();
                bestRole = entry.getKey();
            }
        }

        return new Candidate(fileName, bestRole, bestScore);
    }

}
