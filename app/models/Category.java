package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class Category extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    // カテゴリ名
    @Required
    @Column(nullable = false)
    public String categoryName;


    // 検索用
    public static Model.Find<Integer,Category> find = new Model.Find<Integer,Category>(){};


    // カードリスト
    @OneToMany
    public List<Card> cards = new ArrayList<>();
}
