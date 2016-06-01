package controllers;

import java.util.HashMap;

import play.mvc.*;
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
import utils.Common;
import views.html.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class ErrorController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(error.render());
    }
}
