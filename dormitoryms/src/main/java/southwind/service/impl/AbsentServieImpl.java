package southwind.service.impl;



import southwind.dao.AbsentDao;
import southwind.dao.impl.AbsentDaoImpl;
import southwind.entity.Absent;
import southwind.service.AbsentService;

import java.util.List;

public class AbsentServieImpl implements AbsentService {

    private AbsentDao absentDao = new AbsentDaoImpl();

    @Override
    public void save(Absent absent) {
        Integer save = this.absentDao.save(absent);
        if(save != 1) throw new RuntimeException("添加缺寝记录失败");
    }

    @Override
    public List<Absent> list() {
        return this.absentDao.list();
    }

    @Override
    public List<Absent> search(String key, String value) {
        if(value.equals("")) return this.absentDao.list();
        return this.absentDao.search(key, value);
    }
}
