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

import controllers.Secured.General;
import models.Busho;
import models.Category;
import models.Kengen;
import models.User;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */

@Security.Authenticated(General.class)
public class UserlistController extends Controller {
    @Inject
    private FormFactory formFactory;

    private void init() {
        flash("menu", "userlist");
    }

    public Result index() {
        init();

        List<User> UserList = User.find.all();
        return ok(userlist.render(UserList));

    }
}