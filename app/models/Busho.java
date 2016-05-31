package models;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

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
public class Busho extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    // 部署名
    @Required
    @Column(nullable = false, unique = true)
    public String bushoName;


    // 検索用
    public static Model.Find<Integer,Busho> find = new Model.Find<Integer,Busho>(){};


    // ユーザーリスト
    @OneToMany
    public List<User> users = new ArrayList<>();

    // カードリストfrom
    @OneToMany(mappedBy="fromBusho")
    public List<Card> fromCards = new ArrayList<>();

    // カードリストto
    @OneToMany(mappedBy="toBusho")
    public List<Card> toCards = new ArrayList<>();
}
