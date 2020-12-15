package com.kuang.service;

import com.kuang.dao.StudentMapper;
import com.kuang.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class StudentServiceImpl {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> queryAllStudent(){
        return studentMapper.queryAllStudent();
    }

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
}
