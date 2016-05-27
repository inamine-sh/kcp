package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class Kengen extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    // 権限名
    @Required
    public String kengenName;

    // 権限ランク
    @Required
    public Integer rank;


    // 検索用
    public static Find<Integer,Kengen> find = new Find<Integer,Kengen>(){};


    // ユーザーリスト
    @OneToMany
    public List<User> users = new ArrayList<>();
}
