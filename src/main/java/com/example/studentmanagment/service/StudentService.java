package com.example.studentmanagment.service;

import com.example.studentmanagment.entity.Student;
import com.example.studentmanagment.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Page<Student> findByNameContaining(String name , Pageable pageable) {
      return   studentRepository.findByNameContaining(name, pageable);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }


    public Student addStudent(Student student) {
         return studentRepository.save(student);

    }


    public ResponseEntity<Student> updateStudent(Long key, String password, String name , String email) {

        Optional<Student> lastStudent = studentRepository.findById(key);


        if (lastStudent.isPresent()) {
            Student student = lastStudent.get();
            student.setPassword(password);
            student.setName(name);
            student.setEmail(email);
            studentRepository.save(student);
            return new ResponseEntity<>(HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    public ResponseEntity<Student> deleteStudent(Long key) {
        Optional<Student> lastStudent = studentRepository.findById(key);
        if (lastStudent.isPresent()) {
            studentRepository.delete(lastStudent.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
