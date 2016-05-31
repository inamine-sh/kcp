package controllers;


import java.util.List;
import java.util.Map;

import com.avaje.ebean.ExpressionList;

import controllers.Secured.Hr;
import models.Card;
import models.Category;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.hr;

@Security.Authenticated(Hr.class)
public class HrController extends Controller {

    private void init() {
        flash("menu", "hr");
    }

    public Result index() {
        init();

        ExpressionList<Card> temp = Card.find.where();
        temp = temp.eq("grade", null);

        List <Card> card = temp.findList();

        List<User> user = User.find.all();
        List<Category> category = Category.find.all();


        return ok(hr.render(card,user,category));

    }

 }




