import java.util.Objects;

public class Cat {
    private int age;
    private String name;
    private double weight;
    private boolean hasOwner;

    public Cat(int age, String name, double weight, boolean hasOwner) {
        this.age = age;
        this.name = name;
        this.weight = weight;
        this.hasOwner = hasOwner;
    }

    public Cat()
    {
        this.age = 5;
        this.name = "William";
        this.weight = 2.17;
        this.hasOwner = false;
    }

    @Override
    public boolean equals(Object catObj) {
        if (this == catObj) return true;
        if (!(catObj instanceof Cat cat)) return false;
        return age == cat.age && Double.compare(weight, cat.weight) == 0 && hasOwner == cat.hasOwner && name.equals(cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, weight, hasOwner);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", hasOwner=" + hasOwner +
                '}';
    }
}
