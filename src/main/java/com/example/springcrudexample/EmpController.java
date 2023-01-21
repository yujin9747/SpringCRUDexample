package com.example.springcrudexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    EmpDAO dao;

    public EmpController(EmpDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(String name, float salary, String designation){
        Emp emp = new Emp();
        emp.setName(name);
        emp.setSalary(salary);
        emp.setDesignation(designation);
        dao.save(emp);
        return "redirect:/viewemp";
    }

    @RequestMapping(value="/viewemp")
    public String viewemp(Model model){
        List<Emp> result = dao.getEmployees();
        model.addAttribute("list", result);
        return "viewemp";
    }

    @RequestMapping(value="/empform")
    public String empform(){
        return "empform";
    }

    @RequestMapping(value="/editsave",method = RequestMethod.POST)
    public String editsave(int id, String name, float salary, String designation){
        Emp emp = new Emp();
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        emp.setDesignation(designation);
        dao.update(emp);
        return "redirect:/viewemp";
    }

    @RequestMapping(value="/deleteemp/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable int id){
        dao.delete(id);
        return "redirect:/viewemp";
    }

    @RequestMapping(value="/editemp/{id}", method=RequestMethod.GET)
    public String editemp(@PathVariable int id, Model model){
        Emp emp = dao.getEmpById(id);
        model.addAttribute("emp", emp);
        return "empeditform";
    }
}
