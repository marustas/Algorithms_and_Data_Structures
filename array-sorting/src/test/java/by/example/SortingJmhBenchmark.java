package by.example;

import static by.example.TestDataUtils.createData;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import by.example.sort.Insertion;
import by.example.sort.Merge;
import by.example.sort.Selection;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SortingJmhBenchmark {
	@Param({ "1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000", "11000", "12000", "13000", "14000", "15000", })
	private int n;
	private int[] DATA_FOR_TESTING;

	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder()
				.include(SortingJmhBenchmark.class.getSimpleName())
				.forks(1)
				.threads(1)
				.warmupIterations(5)
				.build();

		new Runner(opt).run();
	}

	@Setup
	public void setup() {
		DATA_FOR_TESTING = createData(n);
	}

	@Benchmark
	public void mergeSort() {
		int[] clone = DATA_FOR_TESTING.clone();
		new Merge.sort(clone);
	}

	@Benchmark
	public void selectionSort() {
		int[] clone = DATA_FOR_TESTING.clone();
		new Selection().sort(clone);
	}

	@Benchmark
	public void insertionSort() {
		int[] clone = DATA_FOR_TESTING.clone();
		new Insertion().sort(clone);
	}
}
