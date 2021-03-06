package chatApp.web.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping(value = "/beans", method = RequestMethod.GET)
public class BeansController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/beansPresenter")
    public ModelAndView beansPresenter(@RequestParam(value = "search",defaultValue = "",required = false)String search,
                                       ModelAndView modelAndView) {
        String[] webApplicationContextBeans = webApplicationContext.getBeanDefinitionNames();
        String[] rootContextBeans = webApplicationContext.getParent().getBeanDefinitionNames();
        System.out.println(search);

        Map model = modelAndView.getModel();
        model.put("beans", Arrays.asList(rootContextBeans).stream()
                .filter(v->v.matches("(?i:.*"+search+".*)"))
                .toArray()
        );
        model.put("webBeans",Arrays.asList(webApplicationContextBeans).stream()
                .filter(v->v.matches("(?i:.*"+search+".*)"))
                .toArray()
        );
        modelAndView.setViewName("/jsp/beans/beans");
        return modelAndView;
    }
    @RequestMapping("/beansStringView")
    ModelAndView beansStringView(){

        return new ModelAndView(new View() {
            @Override
            public String getContentType() {
                return "text/html";
            }

            @Override
            public void render(Map<String, ?> map, HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws Exception {

                String[] webApplicationContextBeans = webApplicationContext.getBeanDefinitionNames();
                String[] rootContextBeans = webApplicationContext.getParent().getBeanDefinitionNames();

                PrintWriter writer =
                        httpServletResponse.getWriter();
                writer.write("<h1>webApplicationContextBeans</h1>\n");
                for (String webApplicationContextBean : webApplicationContextBeans) {
                    writer.write(webApplicationContextBean+"<br>");
                }
                writer.write("<h1>rootContextBeans</h1>");
                for (String rootContextBean : rootContextBeans) {
                    writer.write(rootContextBean+"<br>");
                }
            }
        });
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView except(Exception exception, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/error");
        return modelAndView;
    }
}
