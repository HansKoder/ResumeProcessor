package org.hans.demo.model;

import java.util.Set;

public enum RoleProfile {
    CLOUD(Set.of("aws", "gcp", "terraform", "docker", "kubernetes", "lambdas", "azure")),
    BACKEND(Set.of("java", "spring boot", "kafka", "sql", "mongodb","redis", "microservices")),
    FULLSTACK(Set.of("java", "spring boot", "react", "rest", "frontend","angular", "aws"));

    private final Set<String> requiredSkills;

    RoleProfile(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }
}