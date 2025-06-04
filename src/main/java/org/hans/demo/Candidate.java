package org.hans.demo;

import org.hans.demo.model.RoleProfile;

public record Candidate(String filename, RoleProfile rol, int scores) {
}
