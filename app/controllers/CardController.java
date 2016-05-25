package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import models.Busho;
import models.Card;
import models.Comment;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class CardController extends Controller {
    @Inject
    private FormFactory formFactory;
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
        User user = User.find.where().eq("id", session("user_id")).findUnique();

        Card card1 = Card.find.byId(id);
        List<Comment> comments = Comment.find.where().eq("card", card1).orderBy("postDate").findList();

        return ok(card.render(user, card1, comments));
    }

    public Result newComment(int id) {

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        Comment comment = new Comment();
        comment.card = Card.find.where().eq("id", id).findUnique();
        comment.fromUser = User.find.where().eq("id", session("user_id")).findUnique();
        comment.message = params.get("message")[0];

        comment.insert();

        return redirect(routes.CardController.view(id));
    }

    public Result deleteComment(int cardId, int commentId) {
        User user = User.find.where().eq("id", session("user_id")).findUnique();

        Comment comment = Comment.find.where().eq("id", commentId).findUnique();

        if(comment.fromUser.id == user.id || 2 <= user.kengen.rank) {
            comment.delete();
        }

        return redirect(routes.CardController.view(cardId));
    }
}
