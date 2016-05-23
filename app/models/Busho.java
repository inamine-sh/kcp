package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class Busho extends Model {

    @Id
    public Integer id;

    // 部署名
    @Required
    public String bushoName;


    // 検索用
    public static Find<Integer,Busho> find = new Find<Integer,Busho>(){};

}
