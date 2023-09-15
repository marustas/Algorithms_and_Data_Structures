package by.example.benchmark;

import by.example.impl.Binary;
import by.example.impl.Linear;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BinarySearchBenchmark {
    private static final int LOOP = 1000;
    @Param({"100", "200", "400", "800", "1600", "3200", "6400", "12800", "25600"})
    private int n;
    private int[] DATA_FOR_TESTING;
    private int[] KEYS;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BinarySearchBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        DATA_FOR_TESTING = createData();
        KEYS = createKeys();
    }

    @Benchmark
    public void binarySearch(Blackhole bh) {
        System.out.println(" binarySearch#DATA_FOR_TESTING = " + Arrays.toString(DATA_FOR_TESTING));
        int i = 0;
        while (i < KEYS.length) {
            boolean res = Binary.search(DATA_FOR_TESTING, KEYS[i]);
            bh.consume(res);
            i++;
        }
    }

    @Benchmark
    public void linearSearch(Blackhole bh) {
        int i = 0;
        while (i < KEYS.length) {
            boolean res = Linear.search(DATA_FOR_TESTING, KEYS[i]);
            bh.consume(res);
            i++;
        }
    }

    private int[] createData() {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(10);
        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 1;
        }
        return array;
    }

    private int[] createKeys() {
        Random rnd = new Random();
        int[] keys = new int[LOOP];
        int nxt = rnd.nextInt(n*5);
        for (int i = 0; i < LOOP; i++) {
            keys[i] = nxt;
            nxt += rnd.nextInt(n*5) + 1;
        }
        return keys;
    }
}
