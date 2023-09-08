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

	private static final int LOOP = 1_000;

	@Param({ "100", "1000", "10000" })
	private int n;

	private int[] SORTED_SOURCE_DATA;
	private int[] SORTED_KEYS;

	private int[] UNSORTED_SOURCE_DATA;
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
		SORTED_SOURCE_DATA = createSortedArray(n, n);
		SORTED_KEYS = createSortedArray(LOOP, 3);
		UNSORTED_SOURCE_DATA = generateUnsortedArray(n, n);
		UNSORTED_KEYS = generateUnsortedArray(LOOP, n * 5);
	}

	@Benchmark
	public void binarySearch(Blackhole bh) {
		int i = 0;
		while (i < SORTED_KEYS.length) {
			boolean res = Binary.search(SORTED_SOURCE_DATA, SORTED_KEYS[i]);
			bh.consume(res);
			i++;
		}
	}

	@Benchmark
	public void linearSearch(Blackhole bh) {
		int i = 0;
		while (i < UNSORTED_KEYS.length) {
			boolean res = Linear.search(UNSORTED_SOURCE_DATA, UNSORTED_KEYS[i]);
			bh.consume(res);
			i++;
		}
	}

	@Benchmark
	public void enhancedSearch(Blackhole bh) {
		int i = 0;
		while (i < SORTED_KEYS.length) {
			boolean res = Enhanced.search(SORTED_SOURCE_DATA, SORTED_SOURCE_DATA);
			bh.consume(res);
			i++;
		}
	}

	private int[] createSortedArray(int amountOfElements, int stepBound) {
		Random rnd = new Random();
		int[] keys = new int[amountOfElements];
		int nxt = rnd.nextInt(stepBound);
		for (int i = 0; i < amountOfElements; i++) {
			keys[i] = nxt;
			nxt += 1 + rnd.nextInt(stepBound);
		}
		return keys;
	}

	private int[] generateUnsortedArray(int amountOfElements, int bound) {
		Random rnd = new Random();
		int[] keys = new int[amountOfElements];
		for (int i = 0; i < amountOfElements; i++) {
			keys[i] = rnd.nextInt(bound);
		}
		return keys;
	}
}
