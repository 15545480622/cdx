package com.inspur.vista.labor.cp.util;

/**
 * @Title: StageConstants
 * @Description: 文化宫常量类
 * @Author: gengpeng
 * @CreateDate: 2019/9/12 18:04
 * @Version: 1.0
 */
public class CPConstants {

    /**
     * 有效
     */
    public static int INFO_VALID = 1;

    /**
     * 无效
     */
    public static int INFO_INVALID = 0;

    /**
     * 启用
     */
    public static int ENABLED = 1;

    /**
     * 未启用
     */
    public static int NOT_ENABLED = 0;

    /**
     * 生效中
     */
    public static int EFFECTIVING = 1;

    /**
     * 待生效
     */
    public static int TO_BE_EFFECTIVED = 0;

    /**
     * 待生效
     */
    public static int EXPIRED = -1;

    /**
     * Basic
     */
    public static String BASIC_AUTH = "Basic bGFib3JfY3A6RDhCNEQ0ODY1Q0RDNDBBNDkzNDhGRUY1QUIxNTNBRUI=";

    /**
     * 重复提交键值前缀
     */
    public static final String REPEAT_SUBMIT = "social:security:cp:repeat:submit:";

    /**
     * token前缀
     */
    public static final String TOKEN_KEY = "labor:cp:token:";

    /**
     * 文件类型---文化宫--工会资产证明
     */
    public static final String FILE_TYPE_CP_ASSET_CERTIFICATION = "A1";

    /**
     * 文件类型---文化宫--人员编制的批复
     */
    public static final String FILE_TYPE_CP_STAFFING_REPLY = "A2";

    /**
     * 文件类型---文化宫--工人文化宫标识
     */
    public static final String FILE_TYPE_CP_IDENTITY = "A3";

    /**
     * 文件类型---文化宫--其他
     */
    public static final String FILE_TYPE_CP_OTHER = "A0";

    /**
     * 文件类型---活动--
     */
    public static final String FILE_TYPE_ACTIVITY = "B1";

    /**
     * 文件类型---其他单一类型--banner图
     */
    public static final String FILE_TYPE_BANNER = "C1";

    /**
     * 文件类型---专业人才--照片
     */
    public static final String FILE_TYPE_STAFF_PHOTO = "D1";

    /**
     * 文件类型---专业人才--聘用证书
     */
    public static final String FILE_TYPE_STAFF_CERTIFICATE = "D2";

    /**
     * 文件类型---专业人才--获奖证明
     */
    public static final String FILE_TYPE_STAFF_AWARD_CERTIFICATE = "D3";

    /**
     * 文件类型---专业人才--资质照片
     */
    public static final String FILE_TYPE_STAFF_QUALIFICATION = "D4";

    /**
     * 文件类型---场所--规章制度照片
     */
    public static final String FILE_TYPE_PLACE_REGULATIONS_PHOTO = "E1";

    /**
     * 文件类型---场地--场地照片
     */
    public static final String FILE_TYPE_COURT_COVER_PHOTO = "F1";

    /**
     * 文件类型---安全管理--安全设施照片
     */
    public static final String FILE_TYPE_SECURITY_FACILITIES_PHOTO = "G1";

    /**
     * 文件类型---安全管理--组织演练照片
     */
    public static final String FILE_TYPE_DRILL_PHOTO = "G2";

    /**
     * 文件类型---安全管理--应急预案文件
     */
    public static final String FILE_TYPE_EMERGENCY_PLAN = "G3";

    /**
     * 文件类型---安全管理--安全教育照片
     */
    public static final String FILE_TYPE_SECURITY_EDUCATION_PHOTO = "G4";

    /**
     * 文件类型---规章制度
     */
    public static final String FILE_TYPE_RULES_REGULATIONS = "4";

    /**
     * 文件类型---资产相关
     */
    public static final String FILE_TYPE_CP_ASSET = "5";

    /**
     * 文件类型---安全管理类
     */
    public static final int FILE_TYPE_SAFE = 6;

    /**
     * 文件类型---荣誉证明
     */
    public static final String FILE_TYPE_HONORARY_CERTIFICATE = "7";

    /**
     * 文件类型---资质证明
     */
    public static final String FILE_TYPE_QUALIFICATION = "8";


    /**
     * 收款账号类型---支付宝
     */
    public static final int RECEIVE_ACCOUNT_ALI = 1;

    /**
     * 收款账号类型---微信
     */
    public static final int RECEIVE_ACCOUNT_WECHAT = 2;

    /**
     * 申请单类型---文化宫信息变更
     */
    public static final int APPLY_TYPE_CP_INFO_CHANGE = 1;

    /**
     * 申请单类型---收款账号新增
     */
    public static final int APPLY_TYPE_ACCOUNT_CREATE = 2;

    /**
     * 任务轨迹---提交申请
     */
    public static final int TRACK_STEP_APPLY = 1;

    /**
     * 任务轨迹---区县审核
     */
    public static final int TRACK_STEP_COUNTY_AUDIT = 2;

    /**
     * 任务轨迹---地市审核
     */
    public static final int TRACK_STEP_CITY_AUDIT = 3;

    /**
     * 申请表阶段---待提交
     */
    public static final int APPLY_STEP_READY_COMMIT = 0;

    /**
     * 申请表阶段---区县审核中
     */
    public static final int APPLY_STEP_COUNTY_AUDIT = 1;

    /**
     * 申请表阶段---地市审核中
     */
    public static final int APPLY_STEP_CITY_AUDIT = 2;

    /**
     * 申请表阶段---完成
     */
    public static final int APPLY_STEP_FINISH = 3;

    /**
     * 场所类型---会议室
     */
    public static final String PLACE_TYPE_MEETING = "A";

    /**
     * 场所类型---培训室
     */
    public static final String PLACE_TYPE_TRAINING = "B";

    /**
     * 场所类型---阅览室
     */
    public static final String PLACE_TYPE_READING = "C";

    /**
     * 场所类型---文艺活动室
     */
    public static final String PLACE_TYPE_CULTURAL_ACTIVITIES = "D";

    /**
     * 场所类型---体育活动室
     */
    public static final String PLACE_TYPE_SPORTS = "E";

    /**
     * 场所类型---其他
     */
    public static final String PLACE_TYPE_OTHER = "Z";

    /**
     * 预约类型---同一场不能多次预约
     */
    public static final int RESERVE_TYPE_SCENE_LIMIT_TIMES = 1;

    /**
     * 预约类型---同一场可多次预约
     */
    public static final int RESERVE_TYPE_SCENE_NOT_LIMIT_TIMES = 2;

    /**
     * 场次状态---可预约
     */
    public static final Integer SCENE_STATE_CAN_RESERVE = 1;

    /**
     * 场次状态---不可预约
     */
    public static final Integer SCENE_STATE_NO_RESERVE = 0;

    /**
     * 场次状态---已预约
     */
    public static final Integer SCENE_STATE_HAS_RESERVE = 2;

    /**
     * 预约状态---待支付
     */
    public static final Integer RESERVE_STATE_NOT_PAY = 0;

    /**
     * 预约状态---预约成功
     */
    public static final Integer RESERVE_STATE_SUCCESS = 1;

    /**
     * 预约状态---预约失败
     */
    public static final Integer RESERVE_STATE_FAILURE = 2;

    /**
     * 预约状态---预约关闭
     */
    public static final Integer RESERVE_STATE_CLOSE = 3;

    /**
     * 预约状态---取消预约
     */
    public static final Integer RESERVE_STATE_CANCEL = 4;

    /**
     * 预约状态---完成
     */
    public static final Integer RESERVE_STATE_FINISH = 5;

    /**
     * 支付状态---支付成功
     */
    public static final Integer PAY_STATE_SUCCESS = 1;

    /**
     * 支付状态---支付失败
     */
    public static final Integer PAY_STATE_FAILURE = 2;

    /**
     * 支付状态---退款中
     */
    public static final Integer PAY_STATE_REFUNDING = 3;

    /**
     * 支付状态---已退款
     */
    public static final Integer PAY_STATE_REFUND_SUCCESS = 4;

    /**
     * 支付状态---部分退款成功
     */
    public static final Integer PAY_STATE_PART_REFUND_SUCCESS = 5;

    /**
     * 支付状态---退款失败
     */
    public static final Integer PAY_STATE_REFUND_FAILURE = 6;

    /**
     * 预定途径---APP预约
     */
    public static final String RESERVE_WAY_APP = "1";

    /**
     * 取消预约的原因---不想预定了
     */
    public static final Integer CANCEL_REASON_TYPE_GIVE_UP = 1;

    /**
     * 取消预约的原因---预定错了，重新预定
     */
    public static final Integer CANCEL_REASON_TYPE_RERESERVE = 2;

    /**
     * 取消预约的原因---其他
     */
    public static final Integer CANCEL_REASON_TYPE_OTHER = 3;

    /**
     * 文件上传组
     */
    public static final String FILE_UPLOAD_GROUP = "grwhg";

    /**
     * 预约订单倒计时
     */
    public static final String JOB_GROUP_RESERVE = "labor_cp_reserve_dount_down";

    /**
     * 申请表类型---新文化宫申请
     */
    public static final String APPLY_TYPE_NEW_CP = "new_cp";

    /**
     * 申请表类型---文化宫修改
     */
    public static final String APPLY_TYPE_CP_CHANGE = "cp_info_change";

    /**
     * 申请表类型---新收款账号申请
     */
    public static final String APPLY_TYPE_NEW_ACCOUNT = "new_account";

    /**
     * 申请表类型---月度收支明细审核
     */
    public static final String APPLY_TYPE_MONTHLY_DETAIL = "monthly_detail";

    /**
     * 申请表类型---月度资产负债审核
     */
    public static final String APPLY_TYPE_MONTHLY_DEBT = "monthly_debt";

    /**
     * 待办任务类型---新文化宫申请
     */
    public static final Integer TASK_TYPE_NEW_CP = 1;

    /**
     * 待办任务类型---文化宫修改
     */
    public static final Integer TASK_TYPE_CP_CHANGE = 2;

    /**
     * 待办任务类型---新收款账号申请
     */
    public static final Integer TASK_TYPE_NEW_ACCOUNT = 3;

    /**
     * 待办任务类型---月度收支明细审核
     */
    public static final Integer TASK_TYPE_MONTHLY_DETAIL = 4;

    /**
     * 待办任务类型---月度资产负债审核
     */
    public static final Integer TASK_TYPE_MONTHLY_DEBT = 5;

    /**
     * 申请表审核--通过
     */
    public static final Integer APPLY_CHECK_PASS = 1;

    /**
     * 申请表审核--驳回
     */
    public static final Integer APPLY_CHECK_REJECT = 2;

    /**
     * 路由类型---场所
     */
    public static final String URL_ROUTER_PLACE = "place";

    /**
     * 字典---项目类型
     */
    public static final String DICT_PROJECT_TYPE = "PROJECT_TYPE";

    /**
     * 角色---超级管理员
     */
    public static final String ROLE_SUPER_ADMIN = "WHG_SUPER_MANAGER";
}
