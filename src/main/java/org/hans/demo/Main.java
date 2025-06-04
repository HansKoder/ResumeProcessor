package org.hans.demo;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        File[] files = ResumeUtil.extractResumes("cvs/", ".pdf");

        if (Objects.isNull(files))
            throw new IllegalArgumentException("Must have cvs");


        long start = System.currentTimeMillis();
        CandidateLoop loop = new CandidateLoop();
        List<Candidate> candidatesLoop = loop.getCandidates(Arrays.asList(files));

        System.out.println("Candidates Loop " + candidatesLoop.size() + ", time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        CandidateStream stream = new CandidateStream();
        List<Candidate> candidateStream = stream.getCandidates(Arrays.asList(files));
        System.out.println("Candidates Stream " + candidateStream.size() + ", time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        CandidateParallel parallel = new CandidateParallel();
        List<Candidate> candidateParallel = parallel.getCandidates(Arrays.asList(files));
        System.out.println("Candidates Parallel " + candidateParallel.size() + ", time: " + (System.currentTimeMillis() - start));
    }

}