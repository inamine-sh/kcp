package controllers.Secured;

import play.mvc.Http.Context;
import controllers.routes;
import play.mvc.Result;
import play.mvc.Security;

public class General extends Security.Authenticator {
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("user_id");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.HomeController.index());
    }
}