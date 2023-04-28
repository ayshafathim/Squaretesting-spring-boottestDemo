package com.example.SpringBootTestDemo.service;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.exception.StudentNotFoundException;
import com.example.SpringBootTestDemo.repo.Studentrepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private Studentrepo mockStudentrepo;

    @InjectMocks
    private StudentServiceImpl studentServiceImplUnderTest;

    @Test
    void testAddStudent() {
        // Setup
        final Student student = new Student(0, "name", "address", "rollno");
        final Student expectedResult = new Student(0, "name", "address", "rollno");

        // Configure Studentrepo.save(...).
        final Student student1 = new Student(0, "name", "address", "rollno");
        when(mockStudentrepo.save(new Student(0, "name", "address", "rollno"))).thenReturn(student1);

        // Run the test
        final Student result = studentServiceImplUnderTest.addStudent(student);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAddStudent_StudentrepoThrowsOptimisticLockingFailureException() {
        // Setup
        final Student student = new Student(0, "name", "address", "rollno");
        when(mockStudentrepo.save(new Student(0, "name", "address", "rollno")))
                .thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.addStudent(student))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testGetAllStudent() {
        // Setup
        final List<Student> expectedResult = List.of(new Student(0, "name", "address", "rollno"));

        // Configure Studentrepo.findAll(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollno"));
        when(mockStudentrepo.findAll()).thenReturn(students);

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getAllStudent();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllStudent_StudentrepoReturnsNoItems() {
        // Setup
        when(mockStudentrepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getAllStudent();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetStudentById() {
        // Setup
        final Student expectedResult = new Student(0, "name", "address", "rollno");

        // Configure Studentrepo.findById(...).
        final Optional<Student> student = Optional.of(new Student(0, "name", "address", "rollno"));
        when(mockStudentrepo.findById(0)).thenReturn(student);

        // Run the test
        final Student result = studentServiceImplUnderTest.getStudentById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetStudentById_StudentrepoReturnsAbsent() {
        // Setup
        when(mockStudentrepo.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.getStudentById(0))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void testDeleteStudentById() {
        // Setup
        // Configure Studentrepo.findById(...).
        final Optional<Student> student = Optional.of(new Student(0, "name", "address", "rollno"));
        when(mockStudentrepo.findById(0)).thenReturn(student);

        // Run the test
        final Student result = studentServiceImplUnderTest.deleteStudentById(0);

        // Verify the results
        assertThat(result).isNull();
        verify(mockStudentrepo).delete(new Student(0, "name", "address", "rollno"));
    }

    @Test
    void testDeleteStudentById_StudentrepoFindByIdReturnsAbsent() {
        // Setup
        when(mockStudentrepo.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.deleteStudentById(0))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void testDeleteStudentById_StudentrepoDeleteThrowsOptimisticLockingFailureException() {
        // Setup
        // Configure Studentrepo.findById(...).
        final Optional<Student> student = Optional.of(new Student(0, "name", "address", "rollno"));
        when(mockStudentrepo.findById(0)).thenReturn(student);

        doThrow(OptimisticLockingFailureException.class).when(mockStudentrepo).delete(
                new Student(0, "name", "address", "rollno"));

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.deleteStudentById(0))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testGetStudentByName() {
        // Setup
        final List<Student> expectedResult = List.of(new Student(0, "name", "address", "rollno"));

        // Configure Studentrepo.findByName(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollno"));
        when(mockStudentrepo.findByName("name")).thenReturn(students);

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getStudentByName("name");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetStudentByName_StudentrepoReturnsNoItems() {
        // Setup
        when(mockStudentrepo.findByName("name")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getStudentByName("name");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
