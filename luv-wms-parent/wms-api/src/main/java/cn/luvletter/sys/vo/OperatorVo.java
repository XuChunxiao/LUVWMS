package cn.luvletter.sys.vo;

import cn.luvletter.sys.model.Operator;

import java.util.Date;
import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/20
 */
public class OperatorVo {
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

    /**
     * 角色no
     */
    private List<String> roleList;

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

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
}
