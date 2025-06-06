package org.hans.demo.benchmark;


import org.hans.demo.candidate.CandidateLoop;
import org.hans.demo.candidate.CandidateParallel;
import org.hans.demo.candidate.CandidateStream;
import org.hans.demo.util.ResumeUtil;
import org.hans.demo.shared.Candidate;
import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Fork(value = 2)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class CandidateBenchmark {

    File[] listOfFile;

    private final CandidateLoop candidateLoop = new CandidateLoop();
    private final CandidateStream candidateStream = new CandidateStream();
    private final CandidateParallel candidateParallel = new CandidateParallel();

    @Setup(Level.Invocation)
    public void setUp () {
        listOfFile = ResumeUtil.extractResumes("cvs/", ".pdf");

        if (Objects.isNull(listOfFile))
            throw new IllegalArgumentException("Must have cvs");
    }

    @Benchmark
    public List<Candidate> loop () {
        return candidateLoop.getCandidates(Arrays.asList(listOfFile));
    }

    @Benchmark
    public List<Candidate> stream () {
        return candidateStream.getCandidates(Arrays.asList(listOfFile));
    }

    @Benchmark
    public List<Candidate> parallel () {
        return candidateParallel.getCandidates(Arrays.asList(listOfFile));
    }

}
