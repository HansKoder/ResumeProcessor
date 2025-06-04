package org.hans.demo;

import org.hans.demo.model.RoleProfile;

import java.util.EnumMap;
import java.util.Map;

public class ResumeScorer {

    public static Map<RoleProfile, Integer> scoreResume(String resumeText) {
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

}
