package controllers.Secured;

import play.mvc.Http.Context;
import controllers.routes;
import models.User;
import play.mvc.Result;
import play.mvc.Security;

public class Admin extends Security.Authenticator {
    @Override
    public String getUsername(Context ctx) {

        if(ctx.session().get("user_id") == null) return null;

        User user = User.find.where().eq("id", ctx.session().get("user_id")).findUnique();

        if(user.kengen.rank >= 3) {
            return ctx.session().get("user_id");
        } else {
            return null;
        }

    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.HomeController.index());
    }
}