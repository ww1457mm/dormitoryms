package southwind.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import southwind.entity.DormitoryAdmin;
import southwind.service.DormitoryAdminService;
import southwind.service.impl.DormitoryAdminServiceImpl;

import java.io.IOException;

@WebServlet("/dormitoryAdmin")
public class DormitoryAdminServlet extends HttpServlet {

    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "list":
                req.setAttribute("list", this.dormitoryAdminService.list());
                req.getRequestDispatcher("adminmanager.jsp").forward(req, resp);
            break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list", this.dormitoryAdminService.search(key, value));
                req.getRequestDispatcher("adminmanager.jsp").forward(req, resp);
                break;
            case "save":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String telephone = req.getParameter("telephone");
                this.dormitoryAdminService.save(new DormitoryAdmin(username, password, name, gender, telephone));
                resp.sendRedirect("/dormitoryAdmin?method=list");
                break;
            case "update":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                username = req.getParameter("username");
                password = req.getParameter("password");
                name = req.getParameter("name");
                gender = req.getParameter("gender");
                telephone = req.getParameter("telephone");
                this.dormitoryAdminService.update(new DormitoryAdmin(id, username, password, name, gender, telephone));
                resp.sendRedirect("/dormitoryAdmin?method=list");
                break;
            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                this.dormitoryAdminService.delete(id);
                resp.sendRedirect("/dormitoryAdmin?method=list");
                break;
        }
    }
}
