package controllers;

import java.util.Map;

import javax.inject.Inject;

import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.login;

public class LoginController extends Controller {

    public Result index() {
        return ok(login.render());
    }

    public Result login() {
        Map<String, String[]> params = request().body().asFormUrlEncoded();

        String userId = params.get("userId")[0];
        String password = params.get("password")[0];

        User user = User.find.where().eq("userId", userId).findUnique();

        if (user != null && user.password.equals(password)) {
            session("user_id", Integer.toString(user.id));
            session("user_userId", user.userId);
            session("user_getName", user.getName());
            session("kengen_rank", Integer.toString(user.kengen.rank));
            return redirect(routes.HomeController.index());
        } else {
            return index();
        }

    }

    public Result logout() {
        session().clear();

        return redirect(routes.HomeController.index());
    }
}