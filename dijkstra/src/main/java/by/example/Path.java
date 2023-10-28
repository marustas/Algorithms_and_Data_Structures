package by.example;

public class Path {
    public City city;
    public City prev;
    public Integer dist;
    public Integer index;

    public Path(City city, City prev, Integer dist) {
        this.city = city;
        this.prev = prev;
        this.dist = dist;
        this.index = null;
    }
}
