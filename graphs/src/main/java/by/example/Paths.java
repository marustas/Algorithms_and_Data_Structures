package by.example;

public class Paths {
    Map.City[] path;
    int sp;

    public Paths() {
        path = new Map.City[54];
        sp = 0;
    }

    public Integer shortest(Map.City from, Map.City to) {
        if (from == to) {
            return 0;
        }
        for (int i = 0; i < sp; i++) {
            if (path[i] == from) {
                return null;
            }
        }
        path[sp++] = from;
        Integer shortest = null;

        for (int i = 0; i < from.neighbours.size(); i++) {
            if (from.neighbours.get(i) != null) {
                Map.Connection conn = from.neighbours.get(i);
                Map.City nextCity = conn.city;
                Integer distance = shortest(nextCity, to);
                if ((distance != null) && ((shortest == null) || (shortest > distance + conn.distance))) {
                    shortest = distance + conn.distance;
                }
                if ((shortest != null) && (sp > shortest)) {
                    sp = shortest;
                }
            }
        }
        path[sp--] = null;
        return shortest;
    }
}
