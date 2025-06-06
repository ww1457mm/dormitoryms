package southwind.service;


import southwind.dto.SystemAdminDto;

public interface SystemAdminService {
    public SystemAdminDto login(String username, String password);
}
