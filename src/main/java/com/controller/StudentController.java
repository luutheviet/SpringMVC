package com.controller;

import com.dao.impl.StudentDAOImpl;
import com.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentDAOImpl studentDAO;

    @RequestMapping(value = "student/add")
    public ModelAndView add() {
        return new ModelAndView("student.form", "command", new Student());
    }

    @RequestMapping(value = "student/save")
    public ModelAndView save(@RequestParam(value = "name") String name,
                             @RequestParam(value = "age") String age){
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        if (student.getId() > 0){
            studentDAO.update(student);
        }else{
            studentDAO.insert(student);
        }
        return new ModelAndView("redirect:/student/list");
    }

    @RequestMapping(value = "student/list", method = RequestMethod.GET)
    public ModelAndView listStudent(){
        List<Student> list = new ArrayList<>();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student.list");
        list = studentDAO.list();
        mv.addObject("students", list);
        return mv;
    }

    @RequestMapping(value = "student/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") String id){
        studentDAO.deleteStudent(Integer.parseInt(id));
        return "redirect:/student/list";
    }

    @RequestMapping(value = "student/get/{id}")
    public ModelAndView get(@PathVariable(value = "id") String id){
        Student student = studentDAO.get(Integer.parseInt(id));
        return new ModelAndView("../student.form", "command", student);
    }

    @RequestMapping(value = "student/get/save", method = RequestMethod.POST)
    public ModelAndView saveEdit(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "age") String age){
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        if (student.getId() > 0){
            studentDAO.update(student);
        }else{
            studentDAO.insert(student);
        }
        return new ModelAndView("redirect:/student/list");
    }

    @RequestMapping(value = "/student/json/{id}", method = RequestMethod.GET)
    public @ResponseBody Student viewJson(@PathVariable(value = "id") String id){
        return studentDAO.get(Integer.parseInt(id));
    }


}
