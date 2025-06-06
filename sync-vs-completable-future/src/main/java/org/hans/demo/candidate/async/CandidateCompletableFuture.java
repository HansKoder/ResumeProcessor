package org.hans.demo.candidate.async;

import org.hans.demo.shared.Candidate;
import org.hans.demo.shared.RoleProfile;
import org.hans.demo.util.ResumeUtil;

import java.io.File;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CandidateCompletableFuture {

    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public CompletableFuture<List<Candidate>> getCandidates (List<File> files) {
        List<CompletableFuture<Candidate>> futures = files.stream()
                .map(file -> CompletableFuture.supplyAsync(() -> processFile(file), executor))
                .toList();

        return CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
    }

    private Candidate processFile(File file) {
        String text = ResumeUtil.extractText(file); // expensive
        Map<RoleProfile, Integer> scores = scoreResume(text); // fast
        return getBestMatch(file.getName(), scores);
    }

    private Candidate getBestMatch(String fileName, Map<RoleProfile, Integer> scores) {
        return scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> new Candidate(fileName, entry.getKey(), entry.getValue()))
                .orElse(new Candidate(fileName, null, 0));
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

    public void shutdown() {
        executor.shutdown();
    }

}
