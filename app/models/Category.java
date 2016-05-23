package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class Category extends Model {

    @Id
    public Integer id;

    // カテゴリ名
    @Required
    public String categoryName;


    // 検索用
    public static Find<Integer,Category> find = new Find<Integer,Category>(){};

}
