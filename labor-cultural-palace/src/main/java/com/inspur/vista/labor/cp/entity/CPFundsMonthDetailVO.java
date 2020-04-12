package com.inspur.vista.labor.cp.entity;

import java.io.Serializable;

/**
 * @ClassName: CPFundsMonthDetailVO
 * @Description: 月度经费收支VO
 * @authur: wangxueying01
 * @CreatDate: 2020/1/13 8:49
 */
public class CPFundsMonthDetailVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 文化宫id
     */
    private Long cpId;

    /**
     * 统计月份
     */
    private String month;

    /**
     * 本月上级补贴收入
     */
    private Double inSubsidyThis;

    /**
     * 累计上级补贴收入
     */
    private Double inSubsidySum;

    /**
     * 上级补贴收入备注
     */
    private String inSubsidyMark;

    /**
     * 本月其它收入
     */
    private Double inOtherThis;

    /**
     * 累计其它收入
     */
    private Double inOtherSum;

    /**
     * 其它收入备注
     */
    private String inOtherMark;

    /**
     * 本月事业收入
     */
    private Double inCauseThis;

    /**
     * 累计事业收入
     */
    private Double inCauseSum;

    /**
     * 事业收入备注
     */
    private String inCauseMark;

    /**
     * 本月学历学费收入
     */
    private Double inFeesThis;

    /**
     * 累计学历学费收入
     */
    private Double inFeesSum;

    /**
     * 学历学费收入备注
     */
    private String inFeesMark;

    /**
     * 本月舞蹈培训收入
     */
    private Double inDanceThis;

    /**
     * 累计舞蹈培训收入
     */
    private Double inDanceSum;

    /**
     * 舞蹈培训收入备注
     */
    private String inDanceMark;

    /**
     * 本月计算机培训收入
     */
    private Double inComputerThis;

    /**
     * 累计计算机培训收入
     */
    private Double inComputerSum;

    /**
     * 计算机培训收入备注
     */
    private String inComputerMark;

    /**
     * 本月收入合计
     */
    private Double inSumThis;

    /**
     * 累计收入合计
     */
    private Double inSumSum;

    /**
     * 收入合计备注
     */
    private String inSumMark;

    /**
     * 本月人事费支出
     */
    private Double outPersonThis;

    /**
     * 累计人事费支出
     */
    private Double outPersonSum;

    /**
     * 人事费支出备注
     */
    private String outPersonMark;

    /**
     * 本月公务费支出
     */
    private Double outOfficialThis;

    /**
     * 累计公务费支出
     */
    private Double outOfficialSum;

    /**
     * 公务费支出备注
     */
    private String outOfficialMark;

    /**
     * 本月宣传费支出
     */
    private Double outPropagandaThis;

    /**
     * 累计宣传费支出
     */
    private Double outPropagandaSum;

    /**
     * 宣传费支出备注
     */
    private String outPropagandaMark;

    /**
     * 本月设备费支出
     */
    private Double outEquipThis;

    /**
     * 累计设备费支出
     */
    private Double outEquipSum;

    /**
     * 设备费支出备注
     */
    private String outEquipMark;

    /**
     * 本月修增费支出
     */
    private Double outRepairThis;

    /**
     * 累计修增费支出
     */
    private Double outRepairSum;

    /**
     * 修增费支出备注
     */
    private String outRepairMark;

    /**
     * 本月各项基金支出
     */
    private Double outFundThis;

    /**
     * 累计各项基金支出
     */
    private Double outFundSum;

    /**
     * 各项基金支出备注
     */
    private String outFundMark;

    /**
     * 本月补贴支出
     */
    private Double outSubsidyThis;

    /**
     * 累计补贴支出
     */
    private Double outSubsidySum;

    /**
     * 补贴支出备注
     */
    private String outSubsidyMark;

    /**
     * 本月其它支出
     */
    private Double outOtherThis;

    /**
     * 累计其它支出
     */
    private Double outOtherSum;

    /**
     * 其它支出备注
     */
    private String outOtherMark;

    /**
     * 本月派遣工支出
     */
    private Double outDispatchThis;

    /**
     * 累计派遣工支出
     */
    private Double outDispatchSum;

    /**
     * 派遣工支出备注
     */
    private String outDispatchMark;

    /**
     * 本月活动支出
     */
    private Double outActivityThis;

    /**
     * 累计活动支出
     */
    private Double outActivitySum;

    /**
     * 活动支出备注
     */
    private String outActivityMark;

    /**
     * 本月支出合计
     */
    private Double outSumThis;

    /**
     * 累计支出合计
     */
    private Double outSumSum;

    /**
     * 支出合计备注
     */
    private String outSumMark;

    /**
     * 本月经营结余
     */
    private Double operatingBalanceThis;

    /**
     * 累计经营结余
     */
    private Double operatingBalanceSum;

    /**
     * 经营结余备注
     */
    private String operatingBalanceMark;

    /**
     * 本月期末结余
     */
    private Double endtermBalanceThis;

    /**
     * 累计期末结余
     */
    private Double endtermBalanceSum;

    /**
     * 期末结余备注
     */
    private String endtermBalanceMark;

    /**
     * 负责人
     */
    private String officialsName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getInSubsidyThis() {
        return inSubsidyThis;
    }

    public void setInSubsidyThis(Double inSubsidyThis) {
        this.inSubsidyThis = inSubsidyThis;
    }

    public Double getInSubsidySum() {
        return inSubsidySum;
    }

    public void setInSubsidySum(Double inSubsidySum) {
        this.inSubsidySum = inSubsidySum;
    }

    public String getInSubsidyMark() {
        return inSubsidyMark;
    }

    public void setInSubsidyMark(String inSubsidyMark) {
        this.inSubsidyMark = inSubsidyMark;
    }

    public Double getInOtherThis() {
        return inOtherThis;
    }

    public void setInOtherThis(Double inOtherThis) {
        this.inOtherThis = inOtherThis;
    }

    public Double getInOtherSum() {
        return inOtherSum;
    }

    public void setInOtherSum(Double inOtherSum) {
        this.inOtherSum = inOtherSum;
    }

    public String getInOtherMark() {
        return inOtherMark;
    }

    public void setInOtherMark(String inOtherMark) {
        this.inOtherMark = inOtherMark;
    }

    public Double getInCauseThis() {
        return inCauseThis;
    }

    public void setInCauseThis(Double inCauseThis) {
        this.inCauseThis = inCauseThis;
    }

    public Double getInCauseSum() {
        return inCauseSum;
    }

    public void setInCauseSum(Double inCauseSum) {
        this.inCauseSum = inCauseSum;
    }

    public String getInCauseMark() {
        return inCauseMark;
    }

    public void setInCauseMark(String inCauseMark) {
        this.inCauseMark = inCauseMark;
    }

    public Double getInFeesThis() {
        return inFeesThis;
    }

    public void setInFeesThis(Double inFeesThis) {
        this.inFeesThis = inFeesThis;
    }

    public Double getInFeesSum() {
        return inFeesSum;
    }

    public void setInFeesSum(Double inFeesSum) {
        this.inFeesSum = inFeesSum;
    }

    public String getInFeesMark() {
        return inFeesMark;
    }

    public void setInFeesMark(String inFeesMark) {
        this.inFeesMark = inFeesMark;
    }

    public Double getInDanceThis() {
        return inDanceThis;
    }

    public void setInDanceThis(Double inDanceThis) {
        this.inDanceThis = inDanceThis;
    }

    public Double getInDanceSum() {
        return inDanceSum;
    }

    public void setInDanceSum(Double inDanceSum) {
        this.inDanceSum = inDanceSum;
    }

    public String getInDanceMark() {
        return inDanceMark;
    }

    public void setInDanceMark(String inDanceMark) {
        this.inDanceMark = inDanceMark;
    }

    public Double getInComputerThis() {
        return inComputerThis;
    }

    public void setInComputerThis(Double inComputerThis) {
        this.inComputerThis = inComputerThis;
    }

    public Double getInComputerSum() {
        return inComputerSum;
    }

    public void setInComputerSum(Double inComputerSum) {
        this.inComputerSum = inComputerSum;
    }

    public String getInComputerMark() {
        return inComputerMark;
    }

    public void setInComputerMark(String inComputerMark) {
        this.inComputerMark = inComputerMark;
    }

    public Double getInSumThis() {
        return inSumThis;
    }

    public void setInSumThis(Double inSumThis) {
        this.inSumThis = inSumThis;
    }

    public Double getInSumSum() {
        return inSumSum;
    }

    public void setInSumSum(Double inSumSum) {
        this.inSumSum = inSumSum;
    }

    public String getInSumMark() {
        return inSumMark;
    }

    public void setInSumMark(String inSumMark) {
        this.inSumMark = inSumMark;
    }

    public Double getOutPersonThis() {
        return outPersonThis;
    }

    public void setOutPersonThis(Double outPersonThis) {
        this.outPersonThis = outPersonThis;
    }

    public Double getOutPersonSum() {
        return outPersonSum;
    }

    public void setOutPersonSum(Double outPersonSum) {
        this.outPersonSum = outPersonSum;
    }

    public String getOutPersonMark() {
        return outPersonMark;
    }

    public void setOutPersonMark(String outPersonMark) {
        this.outPersonMark = outPersonMark;
    }

    public Double getOutOfficialThis() {
        return outOfficialThis;
    }

    public void setOutOfficialThis(Double outOfficialThis) {
        this.outOfficialThis = outOfficialThis;
    }

    public Double getOutOfficialSum() {
        return outOfficialSum;
    }

    public void setOutOfficialSum(Double outOfficialSum) {
        this.outOfficialSum = outOfficialSum;
    }

    public String getOutOfficialMark() {
        return outOfficialMark;
    }

    public void setOutOfficialMark(String outOfficialMark) {
        this.outOfficialMark = outOfficialMark;
    }

    public Double getOutPropagandaThis() {
        return outPropagandaThis;
    }

    public void setOutPropagandaThis(Double outPropagandaThis) {
        this.outPropagandaThis = outPropagandaThis;
    }

    public Double getOutPropagandaSum() {
        return outPropagandaSum;
    }

    public void setOutPropagandaSum(Double outPropagandaSum) {
        this.outPropagandaSum = outPropagandaSum;
    }

    public String getOutPropagandaMark() {
        return outPropagandaMark;
    }

    public void setOutPropagandaMark(String outPropagandaMark) {
        this.outPropagandaMark = outPropagandaMark;
    }

    public Double getOutEquipThis() {
        return outEquipThis;
    }

    public void setOutEquipThis(Double outEquipThis) {
        this.outEquipThis = outEquipThis;
    }

    public Double getOutEquipSum() {
        return outEquipSum;
    }

    public void setOutEquipSum(Double outEquipSum) {
        this.outEquipSum = outEquipSum;
    }

    public String getOutEquipMark() {
        return outEquipMark;
    }

    public void setOutEquipMark(String outEquipMark) {
        this.outEquipMark = outEquipMark;
    }

    public Double getOutRepairThis() {
        return outRepairThis;
    }

    public void setOutRepairThis(Double outRepairThis) {
        this.outRepairThis = outRepairThis;
    }

    public Double getOutRepairSum() {
        return outRepairSum;
    }

    public void setOutRepairSum(Double outRepairSum) {
        this.outRepairSum = outRepairSum;
    }

    public String getOutRepairMark() {
        return outRepairMark;
    }

    public void setOutRepairMark(String outRepairMark) {
        this.outRepairMark = outRepairMark;
    }

    public Double getOutFundThis() {
        return outFundThis;
    }

    public void setOutFundThis(Double outFundThis) {
        this.outFundThis = outFundThis;
    }

    public Double getOutFundSum() {
        return outFundSum;
    }

    public void setOutFundSum(Double outFundSum) {
        this.outFundSum = outFundSum;
    }

    public String getOutFundMark() {
        return outFundMark;
    }

    public void setOutFundMark(String outFundMark) {
        this.outFundMark = outFundMark;
    }

    public Double getOutSubsidyThis() {
        return outSubsidyThis;
    }

    public void setOutSubsidyThis(Double outSubsidyThis) {
        this.outSubsidyThis = outSubsidyThis;
    }

    public Double getOutSubsidySum() {
        return outSubsidySum;
    }

    public void setOutSubsidySum(Double outSubsidySum) {
        this.outSubsidySum = outSubsidySum;
    }

    public String getOutSubsidyMark() {
        return outSubsidyMark;
    }

    public void setOutSubsidyMark(String outSubsidyMark) {
        this.outSubsidyMark = outSubsidyMark;
    }

    public Double getOutOtherThis() {
        return outOtherThis;
    }

    public void setOutOtherThis(Double outOtherThis) {
        this.outOtherThis = outOtherThis;
    }

    public Double getOutOtherSum() {
        return outOtherSum;
    }

    public void setOutOtherSum(Double outOtherSum) {
        this.outOtherSum = outOtherSum;
    }

    public String getOutOtherMark() {
        return outOtherMark;
    }

    public void setOutOtherMark(String outOtherMark) {
        this.outOtherMark = outOtherMark;
    }

    public Double getOutDispatchThis() {
        return outDispatchThis;
    }

    public void setOutDispatchThis(Double outDispatchThis) {
        this.outDispatchThis = outDispatchThis;
    }

    public Double getOutDispatchSum() {
        return outDispatchSum;
    }

    public void setOutDispatchSum(Double outDispatchSum) {
        this.outDispatchSum = outDispatchSum;
    }

    public String getOutDispatchMark() {
        return outDispatchMark;
    }

    public void setOutDispatchMark(String outDispatchMark) {
        this.outDispatchMark = outDispatchMark;
    }

    public Double getOutActivityThis() {
        return outActivityThis;
    }

    public void setOutActivityThis(Double outActivityThis) {
        this.outActivityThis = outActivityThis;
    }

    public Double getOutActivitySum() {
        return outActivitySum;
    }

    public void setOutActivitySum(Double outActivitySum) {
        this.outActivitySum = outActivitySum;
    }

    public String getOutActivityMark() {
        return outActivityMark;
    }

    public void setOutActivityMark(String outActivityMark) {
        this.outActivityMark = outActivityMark;
    }

    public Double getOutSumThis() {
        return outSumThis;
    }

    public void setOutSumThis(Double outSumThis) {
        this.outSumThis = outSumThis;
    }

    public Double getOutSumSum() {
        return outSumSum;
    }

    public void setOutSumSum(Double outSumSum) {
        this.outSumSum = outSumSum;
    }

    public String getOutSumMark() {
        return outSumMark;
    }

    public void setOutSumMark(String outSumMark) {
        this.outSumMark = outSumMark;
    }

    public Double getOperatingBalanceThis() {
        return operatingBalanceThis;
    }

    public void setOperatingBalanceThis(Double operatingBalanceThis) {
        this.operatingBalanceThis = operatingBalanceThis;
    }

    public Double getOperatingBalanceSum() {
        return operatingBalanceSum;
    }

    public void setOperatingBalanceSum(Double operatingBalanceSum) {
        this.operatingBalanceSum = operatingBalanceSum;
    }

    public String getOperatingBalanceMark() {
        return operatingBalanceMark;
    }

    public void setOperatingBalanceMark(String operatingBalanceMark) {
        this.operatingBalanceMark = operatingBalanceMark;
    }

    public Double getEndtermBalanceThis() {
        return endtermBalanceThis;
    }

    public void setEndtermBalanceThis(Double endtermBalanceThis) {
        this.endtermBalanceThis = endtermBalanceThis;
    }

    public Double getEndtermBalanceSum() {
        return endtermBalanceSum;
    }

    public void setEndtermBalanceSum(Double endtermBalanceSum) {
        this.endtermBalanceSum = endtermBalanceSum;
    }

    public String getEndtermBalanceMark() {
        return endtermBalanceMark;
    }

    public void setEndtermBalanceMark(String endtermBalanceMark) {
        this.endtermBalanceMark = endtermBalanceMark;
    }

    public String getOfficialsName() {
        return officialsName;
    }

    public void setOfficialsName(String officialsName) {
        this.officialsName = officialsName;
    }

    @Override
    public String toString() {
        return "CPFundsMonthDetailVO{" +
                "id=" + id +
                ", cpId=" + cpId +
                ", month='" + month + '\'' +
                ", inSubsidyThis=" + inSubsidyThis +
                ", inSubsidySum=" + inSubsidySum +
                ", inSubsidyMark='" + inSubsidyMark + '\'' +
                ", inOtherThis=" + inOtherThis +
                ", inOtherSum=" + inOtherSum +
                ", inOtherMark='" + inOtherMark + '\'' +
                ", inCauseThis=" + inCauseThis +
                ", inCauseSum=" + inCauseSum +
                ", inCauseMark='" + inCauseMark + '\'' +
                ", inFeesThis=" + inFeesThis +
                ", inFeesSum=" + inFeesSum +
                ", inFeesMark='" + inFeesMark + '\'' +
                ", inDanceThis=" + inDanceThis +
                ", inDanceSum=" + inDanceSum +
                ", inDanceMark='" + inDanceMark + '\'' +
                ", inComputerThis=" + inComputerThis +
                ", inComputerSum=" + inComputerSum +
                ", inComputerMark='" + inComputerMark + '\'' +
                ", inSumThis=" + inSumThis +
                ", inSumSum=" + inSumSum +
                ", inSumMark='" + inSumMark + '\'' +
                ", outPersonThis=" + outPersonThis +
                ", outPersonSum=" + outPersonSum +
                ", outPersonMark='" + outPersonMark + '\'' +
                ", outOfficialThis=" + outOfficialThis +
                ", outOfficialSum=" + outOfficialSum +
                ", outOfficialMark='" + outOfficialMark + '\'' +
                ", outPropagandaThis=" + outPropagandaThis +
                ", outPropagandaSum=" + outPropagandaSum +
                ", outPropagandaMark='" + outPropagandaMark + '\'' +
                ", outEquipThis=" + outEquipThis +
                ", outEquipSum=" + outEquipSum +
                ", outEquipMark='" + outEquipMark + '\'' +
                ", outRepairThis=" + outRepairThis +
                ", outRepairSum=" + outRepairSum +
                ", outRepairMark='" + outRepairMark + '\'' +
                ", outFundThis=" + outFundThis +
                ", outFundSum=" + outFundSum +
                ", outFundMark='" + outFundMark + '\'' +
                ", outSubsidyThis=" + outSubsidyThis +
                ", outSubsidySum=" + outSubsidySum +
                ", outSubsidyMark='" + outSubsidyMark + '\'' +
                ", outOtherThis=" + outOtherThis +
                ", outOtherSum=" + outOtherSum +
                ", outOtherMark='" + outOtherMark + '\'' +
                ", outDispatchThis=" + outDispatchThis +
                ", outDispatchSum=" + outDispatchSum +
                ", outDispatchMark='" + outDispatchMark + '\'' +
                ", outActivityThis=" + outActivityThis +
                ", outActivitySum=" + outActivitySum +
                ", outActivityMark='" + outActivityMark + '\'' +
                ", outSumThis=" + outSumThis +
                ", outSumSum=" + outSumSum +
                ", outSumMark='" + outSumMark + '\'' +
                ", operatingBalanceThis=" + operatingBalanceThis +
                ", operatingBalanceSum=" + operatingBalanceSum +
                ", operatingBalanceMark='" + operatingBalanceMark + '\'' +
                ", endtermBalanceThis=" + endtermBalanceThis +
                ", endtermBalanceSum=" + endtermBalanceSum +
                ", endtermBalanceMark='" + endtermBalanceMark + '\'' +
                ", officialsName='" + officialsName + '\'' +
                '}';
    }
}
