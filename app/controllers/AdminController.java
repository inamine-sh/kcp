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

import utils.Common;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class AdminController extends Controller {
    private void init() {
        flash("menu", "admin");
    }

    @Inject
    private FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message. The
     * configuration in the <code>routes</code> file means that this method will
     * be called when the application receives a <code>GET</code> request with a
     * path of <code>/</code>.
     */
    public Result index() {
        init();

        return ok(admin.render(formFactory.form(User.class), formFactory.form(Busho.class), formFactory.form(Category.class)));
    }

    public Result newUser() {
        init();

        Map<String, String> params = Common.preparedParams( request().body().asFormUrlEncoded() );
        Form<User> userForm = formFactory.form(User.class).bind(params);

//        if(userForm.hasErrors()) {
//            return badRequest(admin.render(userForm, formFactory.form(Busho.class), formFactory.form(Category.class)));
//        }

        User user = userForm.get();

        user.save();

        return redirect(routes.AdminController.index());
    }

    public Result newBusho() {
        init();

        Map<String, String> params = Common.preparedParams( request().body().asFormUrlEncoded() );
        Form<Busho> bushoForm = formFactory.form(Busho.class).bind(params);

        Busho busho = bushoForm.get();

        busho.save();

        return redirect(routes.AdminController.index());
    }

    public Result newCategory() {
        init();

        Map<String, String> params = Common.preparedParams( request().body().asFormUrlEncoded() );
        Form<Category> categoryForm = formFactory.form(Category.class).bind(params);

        Category category = categoryForm.get();

        category.save();

        return redirect(routes.AdminController.index());
    }

}