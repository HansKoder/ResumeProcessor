# ResumeProcessor 

**ResumeProcessor** is a personal performance lab focused on benchmarking Java code to process resumes in various ways.  
The goal is to **analyze performance trade-offs** between different approaches using modern Java features.

## 🎯 Purpose

- Understand performance implications of different Java constructs.
- Compare imperative and functional styles: `for` loops vs `Streams` vs `parallelStreams`.
- Explore `CompletableFuture`, `ExecutorService`, `@Async`, and other async patterns.
- Evaluate the impact of parallelism and concurrency in resume processing tasks.

## 🧪 Benchmarking Tools

| Tool        | Description                            |
|-------------|----------------------------------------|
| Java 17     | Core language for experimentation      |
| JMH         | Benchmarking micro-framework for Java  |
| Prometheus  | Metrics collection (planned)           |
| Grafana     | Visualize performance metrics (planned)|

## 📁 Structure

This project is divided into multiple Maven modules, each representing a different scenario:

- `loop-vs-streams`: Compare classic `for` loops, `Stream`, and `parallelStream`

## module loop-vs-streams

### Description
This branch has as goal to compare among loop, streams and parallel, this is a small lab to understand better their behavior

### Steps

### 1) Clone resumes
- Locate in the project's root from module loop-vs-streams
```cmd
cd loop-vs-streams/
```

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
- clean and install
```mvn
mvn clean install
```

- execute benchmark

```cmd
java -jar target/loop-vs-streams-1.0-SNAPSHOT.jar 
```

### 3) For scripting (Optional)
- Run CandidateScript.java via IDE 

