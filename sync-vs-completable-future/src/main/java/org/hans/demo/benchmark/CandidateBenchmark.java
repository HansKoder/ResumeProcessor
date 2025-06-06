package org.hans.demo.benchmark;


import org.hans.demo.candidate.async.CandidateCompletableFuture;
import org.hans.demo.candidate.sync.CandidateLoop;
import org.hans.demo.candidate.sync.CandidateParallel;
import org.hans.demo.candidate.sync.CandidateStream;
import org.hans.demo.global.Global;
import org.hans.demo.shared.Candidate;
import org.hans.demo.util.ResumeUtil;
import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Fork(value = 2)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class CandidateBenchmark {

    File[] listOfFile;

    private final CandidateParallel candidateParallel = new CandidateParallel();
    private final CandidateCompletableFuture candidateCF = new CandidateCompletableFuture();

    @Setup(Level.Trial)
    public void setUp () {
        listOfFile = ResumeUtil.extractResumes("cvs/", Global.PDF);

        if (Objects.isNull(listOfFile))
            throw new IllegalArgumentException("Must have cvs");
    }

    @Benchmark
    public List<Candidate> parallel () {
        return candidateParallel.getCandidates(Arrays.asList(listOfFile));
    }

    @Benchmark
    public List<Candidate> completableFuture () throws Exception {
        return candidateCF.getCandidates(Arrays.asList(listOfFile)).get();
    }
}
