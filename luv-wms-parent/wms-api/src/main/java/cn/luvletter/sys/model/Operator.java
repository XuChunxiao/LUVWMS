package cn.luvletter.sys.model;

import java.io.Serializable;
import java.util.Date;

public class Operator implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 帐号
     *
     * @mbggenerated
     */
    private String no;

    /**
     * 密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 地址
     *
     * @mbggenerated
     */
    private String address;

    /**
     * 联系电话
     *
     * @mbggenerated
     */
    private Long phone;

    /**
     * 性别
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private Integer flag;

    /**
     * 创建人ID
     *
     * @mbggenerated
     */
    private Long oprtNo;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String oprtName;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date oprtTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Long getOprtNo() {
        return oprtNo;
    }

    public void setOprtNo(Long oprtNo) {
        this.oprtNo = oprtNo;
    }

    public String getOprtName() {
        return oprtName;
    }

    public void setOprtName(String oprtName) {
        this.oprtName = oprtName;
    }

    public Date getOprtTime() {
        return oprtTime;
    }

    public void setOprtTime(Date oprtTime) {
        this.oprtTime = oprtTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", no=").append(no);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", flag=").append(flag);
        sb.append(", oprtNo=").append(oprtNo);
        sb.append(", oprtName=").append(oprtName);
        sb.append(", oprtTime=").append(oprtTime);
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
        Operator other = (Operator) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getOprtNo() == null ? other.getOprtNo() == null : this.getOprtNo().equals(other.getOprtNo()))
            && (this.getOprtName() == null ? other.getOprtName() == null : this.getOprtName().equals(other.getOprtName()))
            && (this.getOprtTime() == null ? other.getOprtTime() == null : this.getOprtTime().equals(other.getOprtTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getOprtNo() == null) ? 0 : getOprtNo().hashCode());
        result = prime * result + ((getOprtName() == null) ? 0 : getOprtName().hashCode());
        result = prime * result + ((getOprtTime() == null) ? 0 : getOprtTime().hashCode());
        return result;
    }
}