package by.example.benchmark;

import by.example.impl.Binary;
import by.example.impl.Enhanced;
import by.example.impl.Linear;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BinarySearchBenchmark {
    private static final int LOOP = 1000;
    @Param({"1000"})
    private int n;
    private int[] SORTED_DATA_FOR_TESTING;
    private int[] SORTED_KEYS;

    private int[] UNSORTED_DATA_FOR_TESTING;
    private int[] UNSORTED_KEYS;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BinarySearchBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        SORTED_DATA_FOR_TESTING = createSortedData();
        SORTED_KEYS = createSortedKeys();
        UNSORTED_DATA_FOR_TESTING = createUnsortedData();
        UNSORTED_KEYS = createUnsortedKeys();
    }

    @Benchmark
    public void binarySearch(Blackhole bh) {
        int i = 0;
        while (i < SORTED_KEYS.length) {
            boolean res = Binary.search(SORTED_DATA_FOR_TESTING, SORTED_KEYS[i]);
            bh.consume(res);
            i++;
        }
    }

    @Benchmark
    public void linearSearch(Blackhole bh) {
        int i = 0;
        while (i < UNSORTED_KEYS.length) {
            boolean res = Linear.search(UNSORTED_DATA_FOR_TESTING, UNSORTED_KEYS[i]);
            bh.consume(res);
            i++;
        }
    }

    @Benchmark
    public void enhancedSearch(Blackhole bh) {
        int i = 0;
        while (i < SORTED_KEYS.length) {
            boolean res = Enhanced.search(SORTED_DATA_FOR_TESTING, SORTED_DATA_FOR_TESTING);
            bh.consume(res);
            i++;
        }
    }
    private int[] createSortedData() {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(10);
        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 1;
        }
        return array;
    }

    private int[] createSortedKeys() {
        Random rnd = new Random();
        int[] keys = new int[LOOP];
        int nxt = rnd.nextInt(n*5);
        for (int i = 0; i < n; i++) {
            keys[i] = nxt;
            nxt += rnd.nextInt(n*5) + 1;
        }
        return keys;
    }

    private int[] createUnsortedData() {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(10);
        }
        return array;
    }

    private int[] createUnsortedKeys() {
        Random rnd = new Random();
        int[] keys = new int[LOOP];
        for (int i = 0; i < LOOP; i++) {
            keys[i] = rnd.nextInt(n * 5);
        }
        return keys;
    }
}
