package net.haoxf.fasterinput.web.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * wangpeng @ fasterinput
 * kikoroc@gmail.com | https://github.com/kikoroc
 * 15-11-8
 */
@Component
public class ServletConfig implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
//        container.setPort(8080);
        container.setContextPath("/fasterinput");
        container.addInitializers(new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.addListener(new org.springframework.web.util.IntrospectorCleanupListener());

                FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
                encodingFilter.setInitParameter("encoding", "UTF-8");
                encodingFilter.setInitParameter("forceEncoding", "true");
                encodingFilter.addMappingForUrlPatterns(
                        EnumSet.allOf(DispatcherType.class),
                        false,
                        "/*"
                );

                AnnotationConfigWebApplicationContext dispatchContext =
                        new AnnotationConfigWebApplicationContext();
                dispatchContext.register(DispatchServletConfig.class);

                ServletRegistration.Dynamic dispatcher = servletContext.addServlet("fasterinput",
                        new DispatcherServlet(dispatchContext));
                dispatcher.setLoadOnStartup(1);
                dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
                dispatcher.addMapping("/");
            }
        });
    }
}
