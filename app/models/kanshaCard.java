package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

@Entity
public class kanshaCard extends Model {

    @Id
    public Integer id;

    // 送り元ID
    @Required
    public Integer fromId;

    // 送り先ID
    @Required
    public Integer toId;

    // 送り元部署ID
    @Required
    public Integer fromBushoId;

    // 送り先部署ID
    @Required
    public Integer toBushoId;

    // カテゴリID
    public Integer categoryId;

    // タイトル
    @Required
    public String title;

    // 本文
    @Required
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
    public static Find<Integer,kanshaCard> find = new Find<Integer,kanshaCard>(){};

}
