package org.hans.demo.candidate;

import org.hans.demo.shared.Candidate;
import org.hans.demo.shared.RoleProfile;
import org.hans.demo.util.ResumeUtil;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CandidateParallel implements CandidateStrategy {

    public List<Candidate> getCandidates (List<File> files) {
         return files.parallelStream()
                .map(file -> {
                    String text = ResumeUtil.extractText(file);
                    Map<RoleProfile, Integer> scores = scoreResume(text);
                    return getBestMatch(file.getName(), scores);
                }).toList();

    }

    private Candidate getBestMatch (String fileName, Map<RoleProfile, Integer> scores) {
        RoleProfile bestRole = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
        int bestScore = scores.get(bestRole);

        return new Candidate(fileName, bestRole, bestScore);
    }


    private Map<RoleProfile, Integer> scoreResume(String resumeText) {
        return Arrays.stream(RoleProfile.values())
                .parallel()
                .collect(Collectors.toMap(
                        Function.identity(),
                        role -> (int) role.getRequiredSkills().stream()
                                .filter(resumeText::contains)
                                .count(),
                        (a, b) -> a,
                        () -> new EnumMap<>(RoleProfile.class)
                ));
    }

}
