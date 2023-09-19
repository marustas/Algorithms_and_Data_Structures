package by.example;

import org.example.LinkedList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static by.example.TestDataUtils.createData;
import static org.example.LinkedListBenchmark.appendArrays;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class LinkedListJmhBenchmark {
    @Param({"100", "200", "400", "800", "1600", "3200", "6400", "12800", "25600"})
    private int sizeA;
    private int sizeB = 100;
    private int[] arrayA;
    private int[] arrayB;
    private LinkedList linkedListA;
    private LinkedList linkedListB;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(LinkedListJmhBenchmark.class.getSimpleName())
                .forks(1)
                .threads(1)
                .warmupIterations(5)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        arrayA = createData(sizeA);
        arrayB = createData(sizeB);
        linkedListA = new LinkedList(sizeA);
        linkedListB = new LinkedList(sizeB);
    }

    @Benchmark
    public void appendToGrowingArray() {
        int[] cloneA = arrayA.clone();
        int[] cloneB = arrayB.clone();
        appendArrays(cloneA, cloneB);
    }

    @Benchmark
    public void appendToGrowingList() {
        linkedListA.append(linkedListB);
    }

    public void appendGrowingArray() {
        int[] cloneA = arrayA.clone();
        int[] cloneB = arrayB.clone();
        appendArrays(cloneB, cloneA);
    }

    @Benchmark
    public void appendGrowingList() {
        linkedListB.append(linkedListA);
    }
}
