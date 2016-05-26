package controllers;


import java.util.List;
import java.util.Map;

import com.avaje.ebean.ExpressionList;

import models.Card;
import models.Category;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.hr;

public class HrController extends Controller {

    public Result index() {

        ExpressionList<Card> temp = Card.find.where();
        temp = temp.eq("grade", null);

        List <Card> card = temp.findList();

        List<User> user = User.find.all();
        List<Category> category = Category.find.all();


        return ok(hr.render(card,user,category));

    }

 }




