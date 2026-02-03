package cars;

public interface CarStorage<T extends Car> {
    void load(T car);

    T unload();
}
