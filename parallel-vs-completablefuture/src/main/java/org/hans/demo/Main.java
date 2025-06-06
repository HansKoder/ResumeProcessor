package org.hans.demo;

import org.hans.demo.loop.CandidateProcess;
import org.hans.demo.shared.RoleProfile;

import java.util.Map;


public class Main {

    public static void main(String[] args) {

        String text = "Java Developer with experience in java 17, serverless using lambda, ec2, dynamodb";
        Map<RoleProfile, Integer> map = CandidateProcess.scoreResume(text);

        for (Map.Entry<RoleProfile, Integer> item : map.entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }
    }
}