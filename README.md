# ResumeProcessor 

It has as goal to imitate a processor from resumes with the goal give analysis to different candidates with the different profiles
<br/>
The main idea is understanding the performance in Java using different struct to measure performance using JMH - grafana - prometheus.
<br/>
Honestly, this is a personal lab to start to deepen in Java and its performance
<br/> this project will be divided per several branches to test different scenarios

# Stack
| Technology or tool | Short description      |
|--------------------|------------------------|
| Java 17            | -                      |
| JMH                | Make test benchmarking |

# List of branches 

- loop-vs-streams-and-parallel

##  loop-vs-streams-and-parallel

### Description
This branch has as goal to compare among loop, streams and parallel, this is a small lab to understand better their behavior

### Steps

### 1) Clone resumes
- Locate in the project's root

- mkdir cvs

```cmd
mkdir cvs
```

- Copy resume to test

```cmd
cp -r cvs-original/* cvs/
```

- execute PopulateCV only once using intellij


### 2) Benchmark
- compile jar
```mvn
mvn clean install
```

- execute benchmark

```cmd
java -jar target/ResumeProcessor-1.0-SNAPSHOT.jar
```

### 3) For scripting (Optional)
- Using intellij execute 
```cmd
Candidate/CandidateScript
```

