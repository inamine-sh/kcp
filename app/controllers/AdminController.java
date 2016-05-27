package controllers;

import play.data.FormFactory;
import play.data.Form;
import play.mvc.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.List;

import javax.inject.Inject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

import models.Busho;
import models.Category;
import models.Kengen;
import models.User;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class AdminController extends Controller {
    @Inject
    private FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message. The
     * configuration in the <code>routes</code> file means that this method will
     * be called when the application receives a <code>GET</code> request with a
     * path of <code>/</code>.
     */
    public Result index() {
        /*
         * File file = new File("conf/sample.sql"); return
         * ok(userlist.render(file, formFactory.form(User.class)));
         */
        List<User> UserList = User.find.all();
        return ok(admin.render(UserList));

    }

    public Result newUser() {

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        User user = new User();
        user.userId = params.get("userId")[0];
        user.password = params.get("password")[0];
        user.last = params.get("last")[0];
        user.middle = params.get("middle")[0];
        user.first = params.get("first")[0];
        user.yomiLast = params.get("yomiLast")[0];
        user.yomiMiddle = params.get("yomiMiddle")[0];
        user.yomiFirst = params.get("yomiFirst")[0];
        user.bushoId = Busho.find.where().eq("id", params.get("bushoId")[0]).findUnique();
        user.kengen = Kengen.find.where().eq("id", params.get("kengen")[0]).findUnique();

        user.insert();

        return redirect(routes.AdminController.index());
    }

}