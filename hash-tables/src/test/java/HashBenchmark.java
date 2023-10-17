import by.example.HashBuckets;
import by.example.HashLinearProbing;

public class HashBenchmark {
    public static void main(String[] args) {
        String file = "hash-tables/src/main/java/by/example/postnummer.csv";
        HashLinearProbing hashLinearProbing = new HashLinearProbing(file);
        HashBuckets hashBuckets = new HashBuckets(file);
        System.out.println(hashBuckets.lookup(11115));
        System.out.println(hashBuckets.lookup(45293));
        System.out.println(hashBuckets.lookup(98499));
        System.out.println(hashLinearProbing.lookup(11115));
        System.out.println(hashLinearProbing.lookup(45293));
        System.out.println(hashLinearProbing.lookup(98499));
    }
}
