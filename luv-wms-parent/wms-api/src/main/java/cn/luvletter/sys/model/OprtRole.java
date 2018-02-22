package cn.luvletter.sys.model;

import java.io.Serializable;
import java.util.Date;

public class OprtRole implements Serializable {
    private Integer id;

    /**
     * 用户账号
     *
     * @mbggenerated
     */
    private String oprtNo;

    /**
     * 角色编号
     *
     * @mbggenerated
     */
    private String roleNo;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date gmtCreate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOprtNo() {
        return oprtNo;
    }

    public void setOprtNo(String oprtNo) {
        this.oprtNo = oprtNo;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", oprtNo=").append(oprtNo);
        sb.append(", roleNo=").append(roleNo);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OprtRole other = (OprtRole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOprtNo() == null ? other.getOprtNo() == null : this.getOprtNo().equals(other.getOprtNo()))
            && (this.getRoleNo() == null ? other.getRoleNo() == null : this.getRoleNo().equals(other.getRoleNo()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOprtNo() == null) ? 0 : getOprtNo().hashCode());
        result = prime * result + ((getRoleNo() == null) ? 0 : getRoleNo().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        return result;
    }
}