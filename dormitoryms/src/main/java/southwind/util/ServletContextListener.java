package southwind.util;

import jakarta.servlet.ServletContextEvent;

public interface ServletContextListener {


    void contextInitialized(ServletContextEvent sce);

    void contextDestroyed(ServletContextEvent sce);
}
