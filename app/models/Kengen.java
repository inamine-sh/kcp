package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class Kengen extends Model {

    @Id
    public Integer id;

    // 権限名
    @Required
    public String kengenName;

    // 権限ランク
    @Required
    public Integer rank;


    // 検索用
    public static Find<Integer,Kengen> find = new Find<Integer,Kengen>(){};

}
