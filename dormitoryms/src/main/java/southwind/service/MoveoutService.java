package southwind.service;



import southwind.entity.Moveout;

import java.util.List;

public interface MoveoutService {
    public void save(Moveout moveout);
    public List<Moveout> list();
    public List<Moveout> search(String key,String value);
}
