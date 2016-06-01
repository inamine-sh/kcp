package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.avaje.ebean.Ebean;

import controllers.Secured.General;
import models.Busho;
import models.Card;
import models.Category;
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

@Security.Authenticated(General.class)
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
        User user = User.find.where().eq("id", session("user_id")).findUnique();
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

        // パラメータを受け取る
        Map<String, String[]> params = request().body().asFormUrlEncoded();

        Card card1 = new Card();

        try {
            card1.fromUser = user;
            card1.toUser = User.find.where().eq("id", params.get("toUserId")[0]).findUnique();
            card1.fromBusho = card1.fromUser.bushoId;
            card1.toBusho = card1.toUser.bushoId;
            card1.category = !params.get("categoryId")[0].equals("") ? Category.find.where().eq("id", params.get("categoryId")[0]).findUnique() : null;
            card1.kanshaDate = !params.get("kanshaDate")[0].equals("") ? sdfDay.parse( params.get("kanshaDate")[0] ) : null;
            card1.title = !params.get("title")[0].equals("") ? params.get("title")[0] : null;
            card1.message = params.get("message")[0];

            card1.save();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return redirect(routes.UserController.outbox(user.userId));
    }

    public Result editCard() {
        return TODO;
    }

    public Result newComment(int id) {

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        try {
            Comment comment = new Comment();
            comment.card = Card.find.where().eq("id", id).findUnique();
            comment.fromUser = User.find.where().eq("id", session("user_id")).findUnique();
            comment.message = params.get("message")[0];

            comment.insert();
        }catch (Exception e){

        }



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
