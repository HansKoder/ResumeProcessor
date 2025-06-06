package org.hans.demo.candidate;

import org.hans.demo.shared.Candidate;
import org.hans.demo.util.ResumeUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CandidateScript {
    public static void main(String[] args) {
        File[] files = ResumeUtil.extractResumes("loop-vs-streams/cvs/", ".pdf");

        if (Objects.isNull(files))
            throw new IllegalArgumentException("Must have cvs");

        List<CandidateStrategy> impl = List.of(new CandidateLoop(), new CandidateStream(), new CandidateParallel());

        for (CandidateStrategy candidate : impl) {
            long start = System.currentTimeMillis();
            List<Candidate> response = candidate.getCandidates(Arrays.asList(files));
            System.out.printf("Class %s, size of response %s, time elapsed %s \n", candidate.getClass().getName(), response.size(), (System.currentTimeMillis() - start));
        }
    }

}