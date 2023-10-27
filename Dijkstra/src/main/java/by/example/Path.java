package by.example;

public class Path {
    public City city;
    public City prev;
    public Integer dist;
    public Integer index;

    private Path(City city) {
        this.city = city;
        this.prev = null;
        this.index = null;
        this.dist = 15;
    }
}
