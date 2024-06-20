import java.util.Objects;

public class Ket {
    private int age;
    private String name;
    private double weight;
    private boolean hasOwner;

    public Ket(int age, String name, double weight, boolean hasOwner) {
        this.age = age;
        this.name = name;
        this.weight = weight;
        this.hasOwner = hasOwner;
    }

    public Ket()
    {
        this.age = 2;
        this.name = "Thomas";
        this.weight = 3.14;
        this.hasOwner = true;
    }

    @Override
    public boolean equals(Object catObj) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Ket{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", hasOwner=" + hasOwner +
                '}';
    }
}
