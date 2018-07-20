package cent.news.com.newscent.news.channel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ChannelDBBean {

    @Id(autoincrement = true)
    private Long id; //数据库id

    @NotNull
    @Index(unique = true)
    private int channelID;

    private String title;

    private int attval;

    private int type;

    private String alias;

    @Generated(hash = 1841025537)
    public ChannelDBBean(Long id, int channelID, String title, int attval, int type,
            String alias) {
        this.id = id;
        this.channelID = channelID;
        this.title = title;
        this.attval = attval;
        this.type = type;
        this.alias = alias;
    }

    @Generated(hash = 603192808)
    public ChannelDBBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getChannelID() {
        return this.channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAttval() {
        return this.attval;
    }

    public void setAttval(int attval) {
        this.attval = attval;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }


}
