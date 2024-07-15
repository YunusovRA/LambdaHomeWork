import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countMinors = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> potentialEmployees = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18 && (person.getSex() == Sex.MAN ? person.getAge() <= 65 : person.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Количество несовершеннолетних " + countMinors);


        try (PrintWriter conscriptsWriter = new PrintWriter(new File("conscripts.txt"))) {
            for (String conscript : conscripts) {
                conscriptsWriter.println(conscript);
            }
        } catch (Exception e) {
            System.out.println("Не удалось записать файл conscripts.txt: " + e.getMessage());
        }

        try (PrintWriter potentialEmployeesWriter = new PrintWriter(new File("potential_employees.txt"))) {
            for (Person potentialEmployee : potentialEmployees) {
                potentialEmployeesWriter.println(potentialEmployee.getFamily() + " " + potentialEmployee.getName() + ", " + potentialEmployee.getAge() + " лет");
            }
        } catch (Exception e) {
            System.out.println("Не удалось записать файл potential_employees.txt: " + e.getMessage());
        }
    }
}
