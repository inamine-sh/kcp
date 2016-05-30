package models;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

@Entity
public class Card extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    // 送り元ID
    @ManyToOne
    @Required
    @Column(nullable = false)
    public User fromUser;

    // 送り先ID
    @ManyToOne
    @Required
    @Column(nullable = false)
    public User toUser;

    // 送り元部署ID
    @ManyToOne
    @Required
    @Column(nullable = false)
    public Busho fromBusho;

    // 送り先部署ID
    @ManyToOne
    @Required
    @Column(nullable = false)
    public Busho toBusho;

    // カテゴリID
    @ManyToOne
    public Category category;

    // タイトル
    @Required
    @Column(nullable = false)
    public String title;

    // 本文
    @Required
    @Column(nullable = false)
    public String message;

    // 感謝日時
    public Date kanshaDate;

    // 投稿日時
    @CreatedTimestamp
    public Date postDate;

    // 更新日時
    @Version
    public Date updateDate;


    // 以下は人事使用
    // 感謝カードグレード
    @MinLength(1)
    @MaxLength(5)
    public Integer grade;

    // 掲示板掲載期限
    public Date dueDate;

    // 代表月
    public Date daihyoDate;


    // 検索用
    public static Model.Find<Integer,Card> find = new Model.Find<Integer,Card>(){};


    // コメントリスト
    @OneToMany
    public List<Comment> comments = new ArrayList<>();

}
