package br.com.carsoft.repository;

import br.com.carsoft.model.Car;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private static final String FILE_PATH = "cars.json";
    private final Gson gson = new Gson();

    private List<Car> readFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Car>>(){}.getType();
            List<Car> cars = gson.fromJson(reader, listType);
            return cars != null ? cars : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void writeFile(List<Car> cars) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(cars, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Car> findAll() {
        return readFile();
    }

    public void save(Car car) {
        List<Car> cars = readFile();
        cars.add(car);
        writeFile(cars);
    }

    public void update(Car car) {
        List<Car> cars = readFile();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId().equals(car.getId())) {
                cars.set(i, car);
                break;
            }
        }
        writeFile(cars);
    }

    public void delete(String id) {
        List<Car> cars = readFile();
        cars.removeIf(c -> c.getId().equals(id));
        writeFile(cars);
    }

    public Car findById(String id) {
        return readFile().stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
}
