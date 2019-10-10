package top.jplayer.common.constant;

/**
 * @Description: 异常信息枚举类
 * 90开头：系统系统
 * 10、用户相关（用户、团长、经销商、个人中心）
 * 11、团长收益
 *
 * 20、商品相关
 * 30、订单相关
 * 40、支付相关
 */
public enum ExceptionEnums {

    ZUUL_EXCEPTION("900001", "服务请求超时，请稍后重试"),
    TOKEN_VALIDATION_FAILED("900002", "登录失效，请重新登录"),
    SIGN_FAILED("900003", "数字签名异常"),
    REDIS_ERROR("900004", "登录校验失败"),
    TOKEN_VALIDATION_ERROR("900005", "Token校验异常"),
    VALIDATE_PARAMETER_ERROR("900006", "参数验证失败"),
    APP_OSS_TOKEN_ERROR("900007", "生成移动端临时访问OSS的token"),
    PARAM_ERROR("900008", "参数异常"),
    SYSTEM_EXCEPTION("900009", "系统异常，请稍后重试"),
    HYSTRIX_EXCEPTION("900010", "服务请求超时，请稍后重试"),
    CONTENT_SAFE_EXCEPTION("900011", "内容包含敏感信息"),
    PAY_CONFIG_EXCEPTION("900012","找不到相应的配置"),
    GET_ACCESS_TOKEN_ERROR("900013","获取access_token错误"),
    // 10、用户相关（用户、团长、经销商、个人中心）
    IDCARD_EXCEPTION("100001", "身份证校验不通过"),
    INVALID_INVITATION_CODE("100002","无效邀请码"),
    CUSTOMER_NOT_EXIST("100003","客户不存在"),
    CUSTOMER_AVAILABLE_AMOUNT_NOT_ENOUGH("100004","客户可提现金额不足"),
    CUSTOMER_CASH_APPLY_ERROR("100005","客户提现错误"),
    CUSTOMER_REGISTER_ERROR("100006","客户注册失败"),
    SMS_SEND_FREQUENTLY("100007","发送验证码太频繁，稍后再试"),
    SMS_SEND_ERROR("100008","短信验证码发送失败"),
    VERIFY_CODE_TIMEOUT("100009","短信验证码失效"),
    VERIFY_CODE_ERROR("100010","短信验证码错误"),
    CUSTOMER_ALREADY_EXIST("100011","客户已存在"),
    CUSTOMER_ALREADY_FROZEN("100012","客户已冻结"),
    PASSWORD_ERROR("100013","设置密码错误"),
    REGIST_INVITATION_CODE_NOT_EXIST("100014","注册邀请码不存在"),
    PASSWORD_NOT_SET("100015","没有设置密码"),
    GROUP_CUSTOMER_NOT_EXIST("100016","团长不存在"),
    H5_CUSTOMER_NOT_EXIST("100017","被邀请人customerId不存在"),
    REPLY_APPLY_JOIN("100018","被邀请人已经加盟过，不能重复加盟"),
    FORGET_PASSWORD_ERROR("100019","更新密码错误"),
    PASSWORD_VALIDATE_ERROR("100020","密码长度应大于8个字符，并包含至少1个大写字母1个小写字母和1个数字"),
    USERNAME_PASSWORD_ERROR("100021","用户名密码错误"),
    INVITE_EXIST_ERROR("100022","已绑定注册邀请人"),
    INVITE_CODE_ERROR("100023","邀请码匹配不到用户"),
    INVITE_CODE_YOURSELF_ERROR("100023","邀请人不能是自己"),

    DISTRIBUTOR_JOIN_APPROVE_NOT_EXIST_ERROR("100021","加盟申请不存在"),
    DISTRIBUTOR_JOIN_APPROVE_REPEAT_ERROR("100022","重复审核"),
    DISTRIBUTOR_JOIN_NO_COMPANY_ERROR("100023","区域经理没有维护公司信息，不能选择对公转账"),
    TOTAL_STOCK_NUM_ERROR("100024","店主线上库存不足，无法划拨"),
    COMPANY_REPEAT_ERROR("100025","公司信息已存在，无法重复提交"),
    COMPANY_NOT_EXIST_ERROR("100026","公司信息不存在"),
    DISTRIBUTOR_NOT_EXISTS("100027","经销商不存在"),
    DISTRIBUTOR_OFFLINE_NUM_NOT_ENOUGH("100028","经销商线下提货数量不足"),
    DISTRIBUTOR_PICK_UP_PRODUCT_ERROR("100029","经销商线下提货错误"),
    PICK_UP_PRODUCT_NOT_EXISTS("100030","提货申请不存在"),
    PICK_UP_PRODUCT_VERIFY_ERROR("100031","提货申请审批错误"),

    //补货
    REPLENISHMENT_ROLE_ERROR("100040","仅区域经理、店主和店员才能补货"),
    REPLENISHMENT_NOT_EXIST_ERROR("100041","补货申请id不存在"),
    LOWER_STOCKS_ERROR("100042","店主库存不足"),

    // 20、商品相关
    UP_AND_DOWN_SHELF("200000","上下架失败"),
    CUSTOMER_ADDRESS_NOT_EXIST("200001","收货地址不存在"),
    PLACE_AN_ORDER_ERROR("200002","下订单失败"),
    PRODUCT_MARKETING_EXCEED_LIMIT("200003","超过限购数量"),
    // 30、订单相关
    ORDERS_NO_LOGISTIC("300001","订单没有维护物流信息"),
    // 40、支付相关
    ORDERS_NO_EXIST("400001","订单不存在"),
    ORDERS_CANNOT_PAY("400002","订单不符合支付"),
    ORDERS_NO_AUTH("400003","无权操作此订单"),
    // 50、消息
    WEIXIN_TEMPLATE_MESSAGE("500001","微信模板消息发送失败");
    
    private String code;

    private String message;

    ExceptionEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
