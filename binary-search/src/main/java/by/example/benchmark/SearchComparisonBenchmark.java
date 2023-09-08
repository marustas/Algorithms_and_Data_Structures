package by.example.benchmark;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import by.example.impl.Binary;
import by.example.impl.Linear;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SearchComparisonBenchmark {

    private static final int LOOP = 1_000;

    @Param({ "100", "200", "400", "800", "1600", "3200", "6400", "12800", "25600" })
    private int n;

    private int[] SORTED_SOURCE_DATA;

    private int[] UNSORTED_KEYS;

    private final Random rnd = new Random();

    private final Binary binarySearch = new Binary();
    private final Linear linearSearch = new Linear();

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(SearchComparisonBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        SORTED_SOURCE_DATA = createSortedArray(n, 3);
        UNSORTED_KEYS = generateUnsortedArray(LOOP, n * 5);
    }

    @Benchmark
    public void binarySearch(Blackhole bh) {
        for (int key : UNSORTED_KEYS) {
            boolean res = binarySearch.search(SORTED_SOURCE_DATA, key);
            bh.consume(res);
        }
    }

    @Benchmark
    public void linearSearch(Blackhole bh) {
        for (int key : UNSORTED_KEYS) {
            boolean res = linearSearch.search(SORTED_SOURCE_DATA, key);
            bh.consume(res);
        }
    }

    private int[] createSortedArray(int amountOfElements, int stepBound) {
        int[] keys = new int[amountOfElements];
        int nxt = rnd.nextInt(stepBound);
        for (int i = 0; i < amountOfElements; i++) {
            keys[i] = nxt;
            nxt += 1 + rnd.nextInt(stepBound);
        }
        return keys;
    }

    private int[] generateUnsortedArray(int amountOfElements, int bound) {
        int[] keys = new int[amountOfElements];
        for (int i = 0; i < amountOfElements; i++) {
            keys[i] = rnd.nextInt(bound);
        }
        return keys;
    }
}
