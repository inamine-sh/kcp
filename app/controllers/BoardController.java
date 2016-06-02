package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import utils.Common;
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


        return ok(board.render(card, card2, new HashMap<String, String>()));

    }

    public Result search() {
        init();

        List <Card> card2 = null;

        Map<String, String> params = Common.preparedParams( request().body().asFormUrlEncoded() );

        ExpressionList<Card> temp = Card.find.where();

        if (params.containsKey("fromuser")){
            User fromuser = User.find.where().eq("id", Integer.parseInt(params.get("fromuser"))).findUnique();
            temp = temp.eq("fromUser", fromuser);
        }
        if (params.containsKey("touser")){
            User touser = User.find.where().eq("id", Integer.parseInt(params.get("touser"))).findUnique();
            temp = temp.eq("toUser", touser);
        }

        if (params.containsKey("fromBusho")){
            Busho fromBusho = Busho.find.where().eq("id", Integer.parseInt(params.get("fromBusho"))).findUnique();
            temp = temp.eq("fromBusho", fromBusho);
        }

        if (params.containsKey("toBusho")){
            Busho toBusho = Busho.find.where().eq("id", Integer.parseInt(params.get("toBusho"))).findUnique();
            temp = temp.eq("toBusho", toBusho);
        }

        if (params.containsKey("category")){
            Category category = Category.find.where().eq("id", Integer.parseInt(params.get("category"))).findUnique();
            temp = temp.eq("category",category);
        }

        if (params.containsKey("begin")){
            temp = temp.ge("kanshaDate", params.get("begin"));
        }

        if (params.containsKey("end")){
            temp = temp.le("kanshaDate", params.get("end"));
        }

        List <Card> card = temp.findList();

        return ok(board.render(card, card2, params));

    }
}
