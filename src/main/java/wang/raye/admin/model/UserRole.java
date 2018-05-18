package wang.raye.admin.model;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * 用户角色
 */
@Data
public class UserRole {
    @Id
    private Integer userid;
    @Id
    private Integer roleid;

    private Integer creator;

    private Date createtime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}