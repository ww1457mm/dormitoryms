package southwind.service.impl;


import southwind.dao.SystemAdminDao;
import southwind.dao.impl.SystemAdminDaoImpl;
import southwind.dto.SystemAdminDto;
import southwind.entity.SystemAdmin;
import southwind.service.SystemAdminService;

public class SystemAdminServiceImpl implements SystemAdminService {

    private SystemAdminDao systemAdminDao = new SystemAdminDaoImpl();

    @Override
    public SystemAdminDto login(String username, String password) {
        //1、通过username查询数据库
        //2、查询结果为空，username错误
        //3、查询结果不为空，再判断password是否正确
        SystemAdmin systemAdmin = this.systemAdminDao.findByUsername(username);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if(systemAdmin == null){
            systemAdminDto.setCode(-1);
        } else {
            if(!systemAdmin.getPassword().equals(password)){
                systemAdminDto.setCode(-2);
            } else {
                systemAdminDto.setCode(0);
                systemAdminDto.setSystemAdmin(systemAdmin);
            }
        }
        return systemAdminDto;
    }
}
