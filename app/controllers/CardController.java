package controllers;

import java.util.List;

import models.Busho;
import models.Card;
import models.Comment;
import models.User;
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class CardController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    public Result my() {

        return TODO;
    }

    public Result view(int id) {
        Card card1 = Card.find.byId(id);
        List<Comment> comments = card1.comments;

        return ok(card.render(card1, comments));
    }

    public Result newComment() {
        return TODO;
    }
}
