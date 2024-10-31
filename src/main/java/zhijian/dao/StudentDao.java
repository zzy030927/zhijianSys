package zhijian.dao;

import zhijian.entity.Student;

import java.util.List;

public interface StudentDao {

    public boolean Add(Student student);
    public boolean Update(Student student);
    public boolean Delete(Student student);
    public Student FindById(int id);
    public List<Student> findAll();
}
