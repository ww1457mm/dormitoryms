package southwind.dao;



import southwind.entity.Moveout;

import java.util.List;

public interface MoveoutDao {
    public Integer save(Moveout moveout);
    public List<Moveout> list();
    public List<Moveout> search(String key,String value);
}
