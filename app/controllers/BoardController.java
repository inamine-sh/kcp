package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebeaninternal.api.BindParams.Param;

import controllers.Secured.General;
import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

@Security.Authenticated(General.class)
public class BoardController extends Controller{

    private void init() {
        flash("menu", "board");
    }

    public Result index() {
        init();

        Date date = new Date();

        SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-01");
        String now = dateform.format(new Date());

        ExpressionList<Card> line = Card.find.where();
        line = line.ge("dueDate", date);
        line = line.isNotNull("grade");

        ExpressionList<Card> dai = Card.find.where();
        dai = dai.eq("daihyoDate", now);


        List <Card> card = line.findList();
        List<User> user = User.find.all();
        List<Category> category = Category.find.all();
        List <Card> card2 = dai.findList();


        return ok(board.render(card,user,category,card2));

    }

    public Result search() {
        init();

        List <Card> card2 = null;

        List<User> user = User.find.all();
        List<Category> categorys = Category.find.all();

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        ExpressionList<Card> temp = Card.find.where();


        System.out.println(params.toString());

        if (params.containsKey("fromuser") && !params.get("fromuser")[0].equals("-1")){
            User fromuser = User.find.where().eq("id", Integer.parseInt(params.get("fromuser")[0])).findUnique();
            temp = temp.eq("fromUser", fromuser);
        }
        if (params.containsKey("touser") && !params.get("touser")[0].equals("-1")){
            User touser = User.find.where().eq("id", Integer.parseInt(params.get("touser")[0])).findUnique();
            temp = temp.eq("toUser", touser);
        }
        if (params.containsKey("category") && !params.get("category")[0].equals("-1")){
            Category category = Category.find.where().eq("id", Integer.parseInt(params.get("category")[0])).findUnique();
            temp = temp.eq("category",category);
        }

        if (params.containsKey("begin") && !params.get("begin")[0].equals("")){
            temp = temp.ge("kanshaDate", params.get("begin")[0]);
        }

        if (params.containsKey("end") && !params.get("end")[0].equals("")){
            temp = temp.le("kanshaDate", params.get("end")[0]);
        }

        List <Card> card = temp.findList();

        return ok(board.render(card,user,categorys,card2));

    }
}
