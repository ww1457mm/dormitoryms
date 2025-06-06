package southwind.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;

import java.sql.*;
import java.util.Enumeration;

public class JDBCUtil implements ServletContextListener {
    private static String driverName = "com.mysql.cj.jdbc.Driver";
//    private static String url = "jdbc:mysql://localhost:3306/dormitory";
    private static String url = "jdbc:mysql://127.0.0.1:3306/dormitory?serverTimezone=Asia/Shanghai&useSSL=false";
    private static String user = "root";
    private static String password = "123456";

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            context.log("JDBC driver loaded successfully");
        } catch (ClassNotFoundException e) {
            context.log("Error loading JDBC driver", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                context.log("JDBC driver deregistered successfully");
            } catch (Exception e) {
                context.log("Error deregistering JDBC driver", e);
            }
        }
    }

    static {
        // 注册关闭钩子，在JVM退出时执行
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                // 注销所有JDBC驱动
                DriverManager.deregisterDriver(DriverManager.getDriver(url));
                System.out.println("MySQL JDBC驱动已成功注销");
            } catch (Exception e) {
                System.err.println("注销JDBC驱动失败: " + e.getMessage());
            }
        }));
    }

}
