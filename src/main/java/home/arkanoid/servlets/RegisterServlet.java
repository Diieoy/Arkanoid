package home.arkanoid.servlets;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import home.arkanoid.modules.ProdModule;
import home.arkanoid.services.PlayersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Inject
    private PlayersService playersService;

    @Override
    public void init(){
        final Injector injector = Guice.createInjector(new ProdModule());
        injector.injectMembers(this);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        int result = playersService.register(username, password, email);

        if(result > 0){
            response.getWriter().println("Регистрация успешна!");
            response.getWriter().println("Ваш id = " + result);
        }else{
            response.getWriter().println("Ошибка регистрации");
        }
    }
}
