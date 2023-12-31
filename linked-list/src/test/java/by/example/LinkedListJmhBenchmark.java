//package by.example;
//
//import org.example.LinkedList;
//import org.openjdk.jmh.annotations.*;
//import org.openjdk.jmh.runner.Runner;
//import org.openjdk.jmh.runner.RunnerException;
//import org.openjdk.jmh.runner.options.Options;
//import org.openjdk.jmh.runner.options.OptionsBuilder;
//
//import java.util.concurrent.TimeUnit;
//
//import static org.example.LinkedListBenchmark.appendArrays;
//import static org.example.LinkedListBenchmark.createArray;
//
//@State(Scope.Benchmark)
//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
//public class LinkedListJmhBenchmark {
//    @Param({"100", "200", "400", "800", "1600", "3200", "6400", "12800"})
//    private int sizeA;
//    final private int sizeB = 100;
//        private int[] arrayA;
//        private int[] arrayB;
//    private LinkedList linkedListA;
//    private LinkedList linkedListB;
//
//    public static void main(String[] args) throws RunnerException {
//
//        Options opt = new OptionsBuilder()
//                .include(LinkedListJmhBenchmark.class.getSimpleName())
//                .forks(1)
//                .threads(1)
//                .warmupIterations(3)
//                .build();
//        new Runner(opt).run();
//    }
//
//    @Setup(Level.Invocation)
//    public void setup() {
//        arrayA = createArray(sizeA);
//        arrayB = createArray(sizeB);
//        linkedListA = new LinkedList(sizeA);
//        linkedListB = new LinkedList(sizeB);
//    }
//
//        @Benchmark
//    public void appendToGrowingArray() {
//        int[] cloneA = arrayA.clone();
//        int[] cloneB = arrayB.clone();
//        appendArrays(cloneA, cloneB);
//    }
//
//    @Benchmark
//    public void appendToGrowingList() {
//        linkedListA.append(linkedListB);
//    }
//    @Benchmark
//    public void appendGrowingArray() {
//        int[] cloneA = arrayA.clone();
//        int[] cloneB = arrayB.clone();
//        appendArrays(cloneB, cloneA);
//    }
//
//    @Benchmark
//    public void appendGrowingList() {
//        linkedListB.append(linkedListA);
//    }
//}
