package cn.luvletter.main.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Zephyr
 * @Description: 字典
 * @Date 2018/4/2
 */
public class Dictionary implements Serializable {

    private static final long serialVersionUID = -712002438953498525L;

    private Long id;
    private String rid;
    private String paraId;
    private String text;
    private String remark;
    private String pid;
    private Date gmtCreate;
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getParaId() {
        return paraId;
    }

    public void setParaId(String paraId) {
        this.paraId = paraId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", rid='" + rid + '\'' +
                ", paraId='" + paraId + '\'' +
                ", text='" + text + '\'' +
                ", remark='" + remark + '\'' +
                ", pid='" + pid + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
