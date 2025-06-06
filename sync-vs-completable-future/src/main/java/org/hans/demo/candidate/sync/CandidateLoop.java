package org.hans.demo.candidate.sync;

import org.hans.demo.shared.Candidate;
import org.hans.demo.shared.RoleProfile;
import org.hans.demo.util.ResumeUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CandidateLoop implements CandidateStrategy {

    public List<Candidate> getCandidates (List<File> files) {
        List<Candidate> list = new ArrayList<>();
        for (File file: files) {
            String text = ResumeUtil.extractText(file);
            Map<RoleProfile, Integer> scores = scoreResume(text);
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

    private Map<RoleProfile, Integer> scoreResume(String resumeText) {
        Map<RoleProfile, Integer> scores = new EnumMap<>(RoleProfile.class);

        for (RoleProfile role : RoleProfile.values()) {
            int score = 0;
            for (String skill : role.getRequiredSkills()) {
                if (resumeText.contains(skill)) {
                    score++;
                }
            }
            scores.put(role, score);
        }
        return scores;
    }

}
