package southwind.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import southwind.entity.Moveout;
import southwind.service.MoveoutService;
import southwind.service.StudentService;
import southwind.service.impl.MoveoutServiceImpl;
import southwind.service.impl.StudentServiceImpl;

import java.io.IOException;

@WebServlet("/moveout")
public class MoveoutServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();
    private MoveoutService moveoutService = new MoveoutServiceImpl();

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
                req.setAttribute("list",this.studentService.moveoutList());
                req.getRequestDispatcher("moveoutregister.jsp").forward(req, resp);
                break;
            case "moveout":
                String studentIdStr = req.getParameter("studentId");
                Integer studentId = Integer.parseInt(studentIdStr);
                String dormitoryIdStr = req.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String reason = req.getParameter("reason");
                this.moveoutService.save(new Moveout(studentId,dormitoryId,reason));
                resp.sendRedirect("/moveout?method=list");
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list", this.studentService.searchForMoveout(key, value));
                req.getRequestDispatcher("moveoutregister.jsp").forward(req, resp);
                break;
            case "record":
                req.setAttribute("list", this.moveoutService.list());
                req.getRequestDispatcher("moveoutrecord.jsp").forward(req, resp);
                break;
            case "recordSearch":
                key = req.getParameter("key");
                value = req.getParameter("value");
                req.setAttribute("list", this.moveoutService.search(key, value));
                req.getRequestDispatcher("moveoutrecord.jsp").forward(req, resp);
                break;
        }
    }
}
