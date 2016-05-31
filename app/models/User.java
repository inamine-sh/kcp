package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class User extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    // ユーザーID
    @Required
    @Column(nullable = false, unique = true)
    public String userId;

    // パスワード
    @Required
    @Column(nullable = false)
    public String password;

    // 姓
    @Required
    @Column(nullable = false)
    public String last;

    // ミドル
    public String middle;

    // 名
    @Required
    @Column(nullable = false)
    public String first;

    // 姓(読み)
    @Required
    @Column(nullable = false)
    public String yomiLast;

    // ミドル(読み)
    public String yomiMiddle;

    // 名(読み)
    @Required
    @Column(nullable = false)
    public String yomiFirst;

    // 部署ID
    @ManyToOne
    @Required
    @Column(nullable = false)
    public Busho bushoId;

    // 権限ID
    @ManyToOne
    @Required
    @Column(nullable = false)
    public Kengen kengen;

    // 登録日
    @CreatedTimestamp
    public Date registrationDate;


    // 検索用
    public static Model.Find<Integer,User> find = new Model.Find<Integer,User>(){};


    // カードリストfrom
    @OneToMany(mappedBy="fromBusho")
    public List<Card> fromCards = new ArrayList<>();

    // カードリストto
    @OneToMany(mappedBy="toBusho")
    public List<Card> toCards = new ArrayList<>();

    // コメントリスト
    @OneToMany
    public List<Comment> comments = new ArrayList<>();

    public String getName() {
        if (middle.isEmpty()) {
            return last + " " + first;
        } else {
            return last + " " + middle + " " + first;
        }
    }

    public String getYomiName() {
        if (middle.isEmpty()) {
            return yomiLast + " " + yomiFirst;
        } else {
            return yomiLast + " " + yomiMiddle + " " + yomiFirst;
        }
    }
}
