package com.yassine.mcpserver.tools;

import com.yassine.mcpserver.models.Module;
import com.yassine.mcpserver.models.Student;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class McpTools {

    @McpTool(description = "Get all students with their modules and notes")
    public List<Student> getAllStudents() {
        return List.of(
                new Student("Yassine", 25, List.of(
                        new Module("Java", 15.5),
                        new Module("Spring Boot", 16.0),
                        new Module("Angular", 17.5),
                        new Module("Base de Données", 14.0),
                        new Module("DevOps", 15.0),
                        new Module("Réseaux", 13.5),
                        new Module("IA", 16.5)
                )),
                new Student("Imane", 26, List.of(
                        new Module("Java", 13.5),
                        new Module("Spring Boot", 14.0),
                        new Module("Angular", 15.0),
                        new Module("Base de Données", 12.5),
                        new Module("DevOps", 14.5),
                        new Module("Réseaux", 13.0),
                        new Module("IA", 15.5)
                )),
                new Student("Mohamed", 24, List.of(
                        new Module("Java", 16.0),
                        new Module("Spring Boot", 17.0),
                        new Module("Angular", 16.5),
                        new Module("Base de Données", 15.0),
                        new Module("DevOps", 17.5),
                        new Module("Réseaux", 16.0),
                        new Module("IA", 18.0)
                )),
                new Student("Sara", 25, List.of(
                        new Module("Java", 14.0),
                        new Module("Spring Boot", 15.5),
                        new Module("Angular", 14.5),
                        new Module("Base de Données", 13.0),
                        new Module("DevOps", 15.0),
                        new Module("Réseaux", 14.0),
                        new Module("IA", 16.0)
                )),
                new Student("Khalid", 23, List.of(
                        new Module("Java", 12.0),
                        new Module("Spring Boot", 13.5),
                        new Module("Angular", 14.0),
                        new Module("Base de Données", 11.5),
                        new Module("DevOps", 13.0),
                        new Module("Réseaux", 12.5),
                        new Module("IA", 14.0)
                )),
                new Student("Fatima", 26, List.of(
                        new Module("Java", 17.0),
                        new Module("Spring Boot", 16.5),
                        new Module("Angular", 18.0),
                        new Module("Base de Données", 17.5),
                        new Module("DevOps", 16.0),
                        new Module("Réseaux", 15.5),
                        new Module("IA", 18.5)
                )),
                new Student("Omar", 25, List.of(
                        new Module("Java", 13.0),
                        new Module("Spring Boot", 14.5),
                        new Module("Angular", 15.0),
                        new Module("Base de Données", 12.0),
                        new Module("DevOps", 13.5),
                        new Module("Réseaux", 14.0),
                        new Module("IA", 15.5)
                )),
                new Student("Lina", 23, List.of(
                        new Module("Java", 16.5),
                        new Module("Spring Boot", 17.0),
                        new Module("Angular", 16.0),
                        new Module("Base de Données", 15.5),
                        new Module("DevOps", 16.5),
                        new Module("Réseaux", 17.0),
                        new Module("IA", 18.0)
                )),
                new Student("Ahmed", 25, List.of(
                        new Module("Java", 12.5),
                        new Module("Spring Boot", 13.0),
                        new Module("Angular", 12.0),
                        new Module("Base de Données", 11.0),
                        new Module("DevOps", 12.0),
                        new Module("Réseaux", 13.0),
                        new Module("IA", 12.5)
                )),
                new Student("Nour", 27, List.of(
                        new Module("Java", 18.0),
                        new Module("Spring Boot", 17.5),
                        new Module("Angular", 19.0),
                        new Module("Base de Données", 18.0),
                        new Module("DevOps", 17.0),
                        new Module("Réseaux", 16.5),
                        new Module("IA", 19.5)
                ))
        );
    }


    @McpTool(description = "Find a student by their name")
    public Optional<Student> getStudentByName(String name) {
        return getAllStudents().stream()
                .filter(s -> s.getNom().equalsIgnoreCase(name))
                .findFirst();
    }


    @McpTool(description = "Calculate the average grade of a student by name")
    public double getAverageNoteByStudent(String name) {
        return getStudentByName(name)
                .map(student -> student.getModules().stream()
                        .mapToDouble(Module::getNote)
                        .average()
                        .orElse(0.0))
                .orElse(0.0);
    }


    @McpTool(description = "Get top students by average grade")
    public List<Student> getTopStudents(int top) {
        return getAllStudents().stream()
                .sorted(Comparator.comparingDouble(
                        s -> -s.getModules().stream().mapToDouble(Module::getNote).average().orElse(0.0)
                ))
                .limit(top)
                .collect(Collectors.toList());
    }


    @McpTool(description = "Get all modules with grades above a threshold for a student")
    public List<Module> getModulesAboveThreshold(String name, double seuil) {
        return getStudentByName(name)
                .map(student -> student.getModules().stream()
                        .filter(m -> m.getNote() >= seuil)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }


    @McpTool(description = "Calculate the global average of all students")
    public double getGlobalAverage() {
        return getAllStudents().stream()
                .flatMap(s -> s.getModules().stream())
                .mapToDouble(Module::getNote)
                .average()
                .orElse(0.0);
    }
}
