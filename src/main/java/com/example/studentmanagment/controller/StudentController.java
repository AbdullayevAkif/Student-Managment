package com.example.studentmanagment.controller;


import com.example.studentmanagment.entity.Student;
import com.example.studentmanagment.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/class")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "2") int size,
                                    @RequestParam(defaultValue = "id")  String sortBy,
                                    @RequestParam(required = false) String name ) {

         Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        if(name == null)
        {
            return studentService.getAllStudents();
     }



        return  studentService.findByNameContaining(name,pageable).getContent();
    }

    @PostMapping("/StudentAdd")
    public  ResponseEntity<Object> addStudent(@RequestBody Student student, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
       Student studentPost = studentService.addStudent(student);

        return  ResponseEntity.status(HttpStatus.CREATED).body(studentPost);


    }

    @PutMapping("/updateStudent")
    public ResponseEntity<Student> updateStudent(Long id , String password, String name, String email) {
       return studentService.updateStudent(id, password, name, email);
    }



    @DeleteMapping("/deleteStudent")
    public ResponseEntity<Student> deleteStudent(Long id) {
         return studentService.deleteStudent(id);

    }


}
