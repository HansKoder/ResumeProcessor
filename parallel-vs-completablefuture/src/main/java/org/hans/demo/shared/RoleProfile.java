package org.hans.demo.shared;

import java.util.Set;

public enum RoleProfile {
    CLOUD_ENGINEER (Set.of("AWS", "Lambda", "Serverless", "EC2", "DynamoDB", "Terraform", "Python", "Azure", "Api Gateway")),
    BACKEND(Set.of("java", "spring boot", "kafka", "sql", "mongodb","redis", "microservices", "postgres", "java 17", "Java 21", "Quarkus"));

    private final Set<String> listOfSkills;

    RoleProfile(Set<String> listOfSkills) {
        this.listOfSkills = listOfSkills;
    }

    public Set<String> getListOfSkills() {
        return listOfSkills;
    }

    @Override
    public String toString() {
        return "RoleProfile{" +
                "listOfSkills=" + listOfSkills +
                '}';
    }
}
