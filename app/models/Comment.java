package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;

@Entity
public class Comment extends Model {

    @Id
    public Integer id;

    // 感謝カードID
    @Required
    public Integer kanshaId;

    // 送り元ID
    @Required
    public Integer fromId;

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
