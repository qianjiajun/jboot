package org.jot.function;

/**
 * AnimalFunction
 *
 * @author: jiajun.qian@h-shgroup.com
 * @date: 2020/12/31 8:24
 */
@FunctionalInterface
public interface AnimalFunction {

    void eat();

    default void move(Direction direction, long distance) {
        if (direction == null) {
            System.out.println(String.format("Move %d meters in any direction", Math.abs(distance)));
        }
        if (distance == 0L) {
            System.out.println("No movement");
        } else {
            if (distance < 0) {
                Direction trueDirection = direction.getByValue(-direction.value);
                System.out.println(String.format("Move %d meters %s", distance, trueDirection.name));
            } else {
                System.out.println(String.format("Move %d meters %s", distance, direction.name));
            }
        }
    }

    public enum Direction {
        E(1, "east"), S(2, "south"), W(-1, "west"), N(-2, "north");

        private int value;
        private String name;

        Direction(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public Direction getByValue(int value) {
            for (Direction d : Direction.values()) {
                if (d.value == value) {
                    return d;
                }
            }
            return null;
        }

    }

}
