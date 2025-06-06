package org.hans.demo.candidate.script;

import org.hans.demo.candidate.async.CandidateCompletableFuture;
import org.hans.demo.global.Global;
import org.hans.demo.shared.Candidate;
import org.hans.demo.util.ResumeUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class CandidateAsyncScript {

    public static void main(String[] args) throws Exception {
        File[] files = ResumeUtil.extractResumes(Global.DIR_CVS, Global.PDF);

        if (Objects.isNull(files))
            throw new IllegalArgumentException("Must have cvs");

        CandidateCompletableFuture candidateCF = new CandidateCompletableFuture();

        long start = System.currentTimeMillis();
        CompletableFuture<List<Candidate>> futureResult = candidateCF.getCandidates(Arrays.asList(files));
        List<Candidate> candidates = futureResult.get();

        System.out.printf("Class %s, size of response %s, time elapsed %s \n", candidateCF.getClass().getName(), candidates.size(), (System.currentTimeMillis() - start));
        candidateCF.shutdown();
    }

}
