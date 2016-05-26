package controllers;

import java.util.List;
import java.util.Map;

import com.avaje.ebean.ExpressionList;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class BoardController extends Controller{

    public Result index() {

        List<Card> card = Card.find.all();

        List<User> user = User.find.all();
        List<Category> category = Category.find.all();
        return ok(board.render(card,user,category));

    }

    public Result search() {

        List<User> user = User.find.all();
        List<Category> categorys = Category.find.all();

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        ExpressionList<Card> temp = Card.find.where();

        System.out.println(params.toString());

        if (params.get("fromuser")[0] != "-1"){
            User fromuser = User.find.where().eq("id", Integer.parseInt(params.get("fromuser")[0])).findUnique();
            temp = temp.eq("fromUser", fromuser);
        }
        if (params.get("touser")[0] != "-1"){
            User touser = User.find.where().eq("id", Integer.parseInt(params.get("touser")[0])).findUnique();
            temp = temp.eq("toUser", touser);
        }
        if (params.get("category")[0] != "-1"){
            Category category = Category.find.where().eq("id", Integer.parseInt(params.get("category")[0])).findUnique();
            temp = temp.eq("categoryName",category);
        }

        //List<Card> card = Card.find.where().eq("fromUser", fromuser).eq("toUser", touser).eq("categoryName",category).findList();


        List <Card> card = temp.findList();


        return ok(board.render(card,user,categorys));

    }



}
