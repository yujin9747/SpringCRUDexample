package com.example.springcrudexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpDAO {
    JdbcTemplate template;

    @Autowired
    public EmpDAO(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Emp p){
        String sql="insert into emp_table(name,salary,designation) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";
        return template.update(sql);
    }

    public List<Emp> getEmployees(){
        return template.query("select * from emp_table", (rs, row) -> {
            Emp e=new Emp();
            e.setId(rs.getInt(1));
            e.setName(rs.getString(2));
            e.setSalary(rs.getFloat(3));
            e.setDesignation(rs.getString(4));
            return e;
        });
    }

    public int delete(int id){
        String sql = "delete from emp_table where id=" + id + "";
        return template.update(sql);
    }

    public Emp getEmpById(int id){
        String sql = "select * from emp_table where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));
    }

    public int update(Emp emp){
        String sql="update emp_table set name='"+emp.getName()+"', salary="+emp.getSalary()+",designation='"+emp.getDesignation()+"' where id="+emp.getId()+"";
        return template.update(sql);
    }

}
