package application;


import entities.Employes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        System.out.print("Enter salary reference value: ");
        Double refv = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Employes> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employes(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            List<String> emails = list.stream()
                .filter(x -> x.getSalary() > refv)
                .map(x -> x.getEmail())
                .sorted()
                .collect(Collectors.toList());

            System.out.println("Email of people whose salary is more than " + String.format("%.2f", refv) + ":");
            emails.forEach(System.out::println);

            double sum = list.stream()
                    .filter(x -> x.getName().charAt(0) == 'M')
                    .map(x -> x.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();

    }
}