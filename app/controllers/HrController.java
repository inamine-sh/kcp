package controllers;


import play.mvc.Controller;
import play.mvc.Result;
import views.html.hr;

public class HrController extends Controller {

    public Result index() {
        return ok(hr.render());
    }
}