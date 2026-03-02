package edu.iu.habahram.ducksservice.model;

public record DuckData(
        int id,
        String name,
        String type,
        String color,
        int size,
        int price,
        String quackSound,
        boolean flyBehavior
) {

    public String toLine() {
        return String.format("%1$s,%2$s,%3$s,%4$s,%5$s,%6$s,%7$s,%8$s",
                id(), name(), type(), color(), size(), price(), quackSound(), flyBehavior());
    }

    public String toLine(int id) {
        return String.format("%1$s,%2$s,%3$s,%4$s,%5$s,%6$s,%7$s,%8$s",
                id, name(), type(), color(), size(), price(), quackSound(), flyBehavior());
    }

    public static DuckData fromLine(String line) {
        String[] tokens = line.split(",");
        return new DuckData(
                Integer.parseInt(tokens[0]),
                tokens[1],
                tokens[2],
                tokens[3],
                Integer.parseInt(tokens[4]),
                Integer.parseInt(tokens[5]),
                tokens[6],
                Boolean.parseBoolean(tokens[7])
        );
    }
}