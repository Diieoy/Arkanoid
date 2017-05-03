package home.arkanoid.servlets;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import home.arkanoid.entities.Player;
import home.arkanoid.modules.ProdModule;
import home.arkanoid.services.PlayersService;
import home.arkanoid.utils.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
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
        request.getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        String nick = request.getParameter("username");
        String password = request.getParameter("password");

        String password_hash = PasswordUtils.md5Custom(password);

        List<Integer> players = playersService.searchPlayer(nick, null);

        Integer player_id = players.get(0);

        Player player = playersService.getInfo(player_id);

        if(player.getPassword().equals(password_hash)){
            response.addCookie(new Cookie("id", player_id.toString()));
            String hash = PasswordUtils.md5Custom(nick + password);
            response.addCookie(new Cookie("key", hash));
        } else {
            response.getWriter().println("Неправильный пароль");
        }
    }

}
