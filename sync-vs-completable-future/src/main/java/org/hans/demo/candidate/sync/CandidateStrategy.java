package org.hans.demo.candidate.sync;

import org.hans.demo.shared.Candidate;

import java.io.File;
import java.util.List;

public interface CandidateStrategy {
    List<Candidate> getCandidates (List<File> files);
}
