package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class Comment extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    // 感謝カードID
    @ManyToOne
    @Required
    public Card card;

    // 送り元ID
    @ManyToOne
    @Required
    public User fromUser;

    // 本文
    public String message;

    // 投稿日時
    @CreatedTimestamp
    public Date postDate;

    // 更新日時
    @Version
    public Date updateDate;


    // 検索用
    public static Find<Integer,Comment> find = new Find<Integer,Comment>(){};

}
