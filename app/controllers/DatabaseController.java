package controllers;

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
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class DatabaseController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        String sql = "";

        try{
            File file = new File("conf/sample.sql");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));

            String str;
            while((str = br.readLine()) != null){
                System.out.println(str);
                sql += str;
            }

            br.close();

        } catch(IOException e){
            System.out.println(e);
        }


        // System.out.println(sql);
        SqlUpdate insert = Ebean.createSqlUpdate(sql);
        insert.execute();

        return ok("データベースにサンプルデータを挿入しました。");
    }

}
