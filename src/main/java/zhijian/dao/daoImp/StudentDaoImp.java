package zhijian.dao.daoImp;

import zhijian.dao.StudentDao;
import zhijian.entity.Student;

import java.util.Collections;
import java.util.List;

public class StudentDaoImp implements StudentDao {
    @Override
    public boolean Add(Student student) {
        return false;
    }

    @Override
    public boolean Update(Student student) {
        return false;
    }

    @Override
    public boolean Delete(Student student) {
        return false;
    }

    @Override
    public Student FindById(int id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return Collections.emptyList();
    }
}
