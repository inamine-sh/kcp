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

import controllers.Secured.Admin;
import models.Busho;
import models.Card;
import models.Category;
import models.Kengen;
import models.User;

import utils.Common;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */

@Security.Authenticated(Admin.class)
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

        flash("status", "all");

        return ok(admin.render(formFactory.form(User.class), formFactory.form(Busho.class), formFactory.form(Category.class), new User(), new Busho(), new Category()));
    }

    public Result viewEditUser(int userId) {
        init();

        flash("status", "user");

        User user = User.find.where().eq("id", userId).findUnique();

        return ok(admin.render(formFactory.form(User.class), formFactory.form(Busho.class), formFactory.form(Category.class), user, new Busho(), new Category()));

    }

    public Result editUser() {
        init();

        Map<String, String> params = Common.preparedParams( request().body().asFormUrlEncoded() );
        Form<User> userForm = formFactory.form(User.class).bind(params);

        try {
            User user = userForm.get();

            if(user.id == null) {
                user.save();
            } else {
                user.update();
            }
        } catch (Exception e) {
            return badRequest(error.render());
        }

        return redirect(routes.AdminController.index());
    }

    public Result deleteUser(int userId) {
        init();

        return TODO;
    }


    public Result viewEditBusho(int bushoId) {
        init();

        flash("status", "busho");

        Busho busho = Busho.find.where().eq("id", bushoId).findUnique();

        return ok(admin.render(formFactory.form(User.class), formFactory.form(Busho.class), formFactory.form(Category.class), new User(), busho, new Category()));

    }

    public Result editBusho() {
        init();

        Map<String, String> params = Common.preparedParams( request().body().asFormUrlEncoded() );
        Form<Busho> bushoForm = formFactory.form(Busho.class).bind(params);

        try {
            Busho busho = bushoForm.get();

            if(busho.id == null) {
                busho.save();
            } else {
                busho.update();
            }
        } catch (Exception e) {
            return badRequest(error.render());
        }

        return redirect(routes.AdminController.index());
    }

    public Result deleteBusho(int bushoId) {
        init();

        List<Card> cards1 = Card.find.where().eq("from_busho_id", bushoId).findList();
        for(Card card: cards1) {
            card.fromBusho = Busho.find.where().eq("id", 1).findUnique();
            card.update();
        }

        List<Card> cards2 = Card.find.where().eq("to_busho_id", bushoId).findList();
        for(Card card: cards2) {
            card.toBusho = Busho.find.where().eq("id", 1).findUnique();
            card.update();
        }

        List<User> users = User.find.where().eq("busho_id_id", bushoId).findList();
        for(User user: users) {
            user.bushoId = Busho.find.where().eq("id", 1).findUnique();
            user.update();
        }

        Busho busho = Busho.find.where().eq("id", bushoId).findUnique();
        busho.delete();

        return redirect(routes.AdminController.index());
    }


    public Result viewEditCategory(int categoryId) {
        init();

        flash("status", "category");

        Category category = Category.find.where().eq("id", categoryId).findUnique();

        return ok(admin.render(formFactory.form(User.class), formFactory.form(Busho.class), formFactory.form(Category.class), new User(), new Busho(), category));
    }

    public Result editCategory() {
        init();

        Map<String, String> params = Common.preparedParams( request().body().asFormUrlEncoded() );
        Form<Category> categoryForm = formFactory.form(Category.class).bind(params);

        try {
            Category category = categoryForm.get();

            if(category.id == null) {
                category.save();
            } else {
                category.update();
            }
        } catch (Exception e) {
            return badRequest(error.render());
        }

        return redirect(routes.AdminController.index());
    }

    public Result deleteCategory(int categoryId) {
        init();


        List<Card> cards = Card.find.where().eq("category_id", categoryId).findList();
        for(Card card: cards) {
            card.category = Category.find.where().eq("id", 1).findUnique();

            card.update();
        }

        Category category = Category.find.where().eq("id", categoryId).findUnique();
        category.delete();

        return redirect(routes.AdminController.index());
    }

}