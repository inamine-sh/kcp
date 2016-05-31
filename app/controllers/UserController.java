package controllers;

import java.util.List;

import controllers.Secured.General;
import models.Card;
import models.User;
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

@Security.Authenticated(General.class)
public class UserController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index(String userId) {

        return inbox(userId);
    }

    public Result my() {
        User loginUser = User.find.where().eq("id", session("user_id")).findUnique();

        return index(loginUser.userId);
    }

    public Result inbox(String userId) {
        flash("submenu", "inbox");

        User viewUser  = User.find.where().eq("userId", userId).findUnique();

        List<Card> cards = Card.find.where().eq("toUser", viewUser).findList();

        return ok(user.render(viewUser, cards));
    }

    public Result outbox(String userId) {
        flash("submenu", "outbox");

        User viewUser  = User.find.where().eq("userId", userId).findUnique();

        List<Card> cards = Card.find.where().eq("fromUser", viewUser).findList();

        return ok(user.render(viewUser, cards));
    }
}
