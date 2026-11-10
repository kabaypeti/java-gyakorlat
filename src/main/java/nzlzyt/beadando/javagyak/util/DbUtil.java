package nzlzyt.beadando.javagyak.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;

public class DbUtil {

    public static <T> List<List<Object>> mapData(List<T> data, DataConverter<T> converter) {
        List<List<Object>> mapList = new ArrayList<>();
        for (T d : data) {
            mapList.add(converter.convert(d));
        }
        return mapList;
    }

    public static <D,R extends JpaRepository<D, ?>> void seedTableFromFile(File file, R repository, Function<Map<String,String>, D> factory) throws FileNotFoundException {
        seedTableFromFile(file, repository, factory, repository.count() == 0);
    }

    public static <D,R extends JpaRepository<D, ?>> void seedTableFromFile(File file, R repository, Function<Map<String,String>, D> factory, boolean force) throws FileNotFoundException {
        if (force) {
            Scanner sc = new Scanner(file);
            String keyLine = sc.nextLine().trim();
            var keys = keyLine.split("\t");

            List<D> entries = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine().trim();
                var data = line.split("\t");
                HashMap<String, String> mapped = new HashMap<>();
                for (int i = 0; i < keys.length; ++i) {
                    mapped.put(keys[i], data[i]);
                }

                entries.add(factory.apply(mapped));
            }
            repository.saveAll(entries);
        }
    }

    @FunctionalInterface
    public interface DataConverter<T> {
        List<Object> convert(T input);
    }

}
