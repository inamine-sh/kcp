package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class User extends Model {

    @Id
    public Integer id;

    // ユーザーID
    @Required
    public String userId;

    // パスワード
    @Required
    public String password;

    // 姓
    @Required
    public String last;

    // ミドル
    public String middle;

    // 名
    @Required
    public String first;

    // 姓(読み)
    @Required
    public String yomiLast;

    // ミドル(読み)
    public String yomiMiddle;

    // 名(読み)
    @Required
    public String yomiFirst;

    // 部署ID
    @ManyToOne
    @Required
    public Busho bushoId;

    // 権限ID
    @ManyToOne
    @Required
    public Kengen kengen;

    // 登録日
    @CreatedTimestamp
    public Date registrationDate;


    // 検索用
    public static Find<Integer,User> find = new Find<Integer,User>(){};


    // カードリスト
    @OneToMany
    public List<Card> cards = new ArrayList<>();

    // コメントリスト
    @OneToMany
    public List<Comment> comments = new ArrayList<>();
}
