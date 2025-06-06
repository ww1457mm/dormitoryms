package southwind.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import southwind.entity.*;
import southwind.service.AbsentService;
import southwind.service.BuildingService;
import southwind.service.DormitoryService;
import southwind.service.StudentService;
import southwind.service.impl.AbsentServieImpl;
import southwind.service.impl.BuildingServiceImpl;
import southwind.service.impl.DormitoryServiceImpl;
import southwind.service.impl.StudentServiceImpl;

import java.io.IOException;
import java.util.List;


@WebServlet("/absent")
public class AbsentServlet extends HttpServlet {

    private BuildingService buildingService = new BuildingServiceImpl();
    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    private StudentService studentService = new StudentServiceImpl();
    private AbsentService absentService = new AbsentServieImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "init":
                List<Building> buildingList = this.buildingService.list();
                List<Dormitory> dormitoryList = this.dormitoryService.findByBuildingId(buildingList.get(0).getId());
                List<Student> studentList = this.studentService.findByDormitoryId(dormitoryList.get(0).getId());
                req.setAttribute("buildingList", buildingList);
                req.setAttribute("dormitoryList", dormitoryList);
                req.setAttribute("studentList",studentList);
                req.getRequestDispatcher("absentregister.jsp").forward(req, resp);
                break;
            case "save":
                String buildingIdStr = req.getParameter("buildingId");
                Integer buildingId = Integer.parseInt(buildingIdStr);
                String dormitoryIdStr = req.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String studentIdStr = req.getParameter("studentId");
                Integer studentId = Integer.parseInt(studentIdStr);
                String reason = req.getParameter("reason");
                String date = req.getParameter("date");
                HttpSession session = req.getSession();
                DormitoryAdmin dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
                this.absentService.save(new Absent(buildingId, dormitoryId, studentId, dormitoryAdmin.getId(), date,reason));
                resp.sendRedirect("/absent?method=init");
                break;
            case "list":
                req.setAttribute("list", this.absentService.list());
                req.getRequestDispatcher("absentrecord.jsp").forward(req, resp);
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list", this.absentService.search(key, value));
                req.getRequestDispatcher("absentrecord.jsp").forward(req, resp);
                break;
        }
    }
}
