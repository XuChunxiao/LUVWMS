package cn.luvletter.base.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Zephyr Ji
 * @ Description: 批量新增库位
 * @ Date 2018/3/28
 */
public class StorageLocationVo {
    /**
     * 库位开始编码
     */
    @NotEmpty(message = "库位开始编码不能为空 ")
    private String storageLocationStart;
    /**
     * 库位结束编码
     */
    @NotEmpty(message = "库位结束编码不能为空 ")
    private String storageLocationEnd;
    /**
     * 区域编号
     */
    @NotEmpty(message = "区域编号不能为空 ")
    private String areaNo;
    /**
     * 库位类型
     */
    @NotEmpty(message = "库位类型不能为空 ")
    private String storageLocationType;

    public String getStorageLocationStart() {
        return storageLocationStart;
    }

    public void setStorageLocationStart(String storageLocationStart) {
        this.storageLocationStart = storageLocationStart;
    }

    public String getStorageLocationEnd() {
        return storageLocationEnd;
    }

    public void setStorageLocationEnd(String storageLocationEnd) {
        this.storageLocationEnd = storageLocationEnd;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getStorageLocationType() {
        return storageLocationType;
    }

    public void setStorageLocationType(String storageLocationType) {
        this.storageLocationType = storageLocationType;
    }

    @Override
    public String toString() {
        return "StorageLocationVo{" +
                "storageLocationStart='" + storageLocationStart + '\'' +
                ", storageLocationEnd='" + storageLocationEnd + '\'' +
                ", areaNo='" + areaNo + '\'' +
                ", storageLocationType='" + storageLocationType + '\'' +
                '}';
    }
}
