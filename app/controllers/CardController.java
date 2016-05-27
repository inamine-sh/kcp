package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.avaje.ebean.Ebean;

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

    public Result view(int id) {
        User user = User.find.where().eq("id", session("user_id")).findUnique();

        Card card1 = Card.find.byId(id);

        List<Comment> comments = Comment.find.where().eq("card", card1).orderBy("postDate").findList();

        return ok(card.render(user, card1, comments));
    }

    public Result newCard() {
        return TODO;
    }

    public Result editCard() {
        return TODO;
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

    public Result setGrade(int cardId) {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        Card card1 = Card.find.byId(cardId);

        try {
            if( !params.get("grade")[0].equals("") ) {
                card1.grade = Integer.parseInt( params.get("grade")[0] );
            } else {
                card1.grade = null;
            }

            if( !params.get("dueDate")[0].equals("") ){
                card1.dueDate = sdfDay.parse( params.get("dueDate")[0] );
            } else {
                card1.dueDate = null;
            }

            if( !params.get("daihyoDate")[0].equals("") ){
                card1.daihyoDate = sdfMonth.parse( params.get("daihyoDate")[0] );
            } else {
                card1.daihyoDate =null;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        card1.update();

        return redirect(routes.CardController.view(cardId));
    }

}
