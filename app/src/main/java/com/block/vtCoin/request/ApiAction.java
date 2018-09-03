package com.block.vtCoin.request;

public class ApiAction {
//    public static final String VtCoinUrl="https://www.vtcoin.cn/";

    public static final String VtCoinUrl ="http://1711255wc1.iok.la/";//测试环境
    //注册
    public static final String Account_Register = "Account/Register";
    //登录
    public static final String Account_Login= "Account/Login";

    //拿连接容云的Token
    public static final String Chat_Token= "Chat/Token";

    //获取验证码
    public static final String Account_SendSmsCode= "Account/SendSMSCode";
    //验证验证码
    public static final String Account_VerifyRegSMSCode= "Account/VerifyRegSMSCode";
    //重置登录密码？
    public static final String Account_ResetLoginPas= "Account/ForgotAdvancedPasswordBySMS";
    //退出登录？
    public static final String Account_Logout= "Account/Logout";
    //修改登录密码
    public static final String Account_ModifyLoginPassword= "Account/ChangeBasicPassword";
    //修改交易密码，设置交易密码共用一个接口
    public static final String Account_ChangeAdvancedPassword= "Account/ChangeAdvancedPassword";
    //获取用户信息
    public static final String Account_GetUserInfo= "Account/GetUserInfo";
    //获取市场行情
    public static final String Markets= "/api/v1/markets";
    //获取某一条市场行情
    public static final String Market= "/api/v1/market";
    //是否成为交易商
    public static final String Preference_IsDealer= "Preference/IsDealer";
    //查询成为交易商的权限
    public static final String Preference_QueryDealerQualification= "Preference/QueryDealerQualification";
    //拉起用户信息
    public static final String Account_GetPersonal_Info= "Account/GetPersonalInfo";
    //是否有未处理的订单
    public static final String Order_UnhandOrderNumber= "Order/UnhandleOrderNumber";
    //上传图片
    public static final String Preference_UpdateImage= "Preference/UpdateImage";
    //修改昵称
    public static final String Account_UpdateNickName= "Account/UpdateNickName";
    //修改签名
    public static final String Account_ResetComment= "Account/ResetComment";
    //登录历史
    public static final String Account_LoginHistory= "Account/LoginHistory";
    //更改语言
    public static final String Account_RestLang= "Account/RestLang";
    //获取钱包信息
    public static final String Account_Capitals= "account/Capitals";
    //获取余额地址
    public static final String Preference_ListUserAddressWithBanlance= "Preference/ListUserAddressWithBanlance";
    //获取我的关注
    public static final String Preference_ListFollowUser= "Preference/ListFollowUser";
    //查询买入订单信息
    public static final String Exchange_QuerySellRestingOrder = "exchange/QuerySellRestingOrder";
    //查询卖出订单信息
    public static final String Exchange_QueryBuyRestingOrder = "exchange/QueryBuyRestingOrder";
    //查询订单商在线信息
    public static final String Chat_GetChatTokenByNameWithMany = "Chat/GetChatTokenByNameWithMany";

    //通过用户名拿到对方的chatId，用于聊天
    public static final String Chat_GetChatTokenByName = "Chat/GetChatTokenByName";
    //获取订单价格
    public static final String Deal_Ticket = "api/v1/l_ticket";
    //我们平台收的手续费率
    public static final String Config_AppFeeRate = "Config/AppFeeRate";
    //手续费率
    public static final String Exchange_QueryConnectionSellOder = "exchange/QueryConnectionSellOder";
    //是否登录
    public static final String Account_Islogin="Account/Islogin";
    //根据订单号查询某一条订单的信息
    public static final String exchange_QueryOneSellOder="exchange/QueryOneSellOder";
    //获取交易商广告
    public static final String Preference_ListManyProvi="Preference/ListManyProvi";





    //修改登录密码
    public static final String USER_CHANGEPASSWORD= "User/ChangePassword";
    //修改交易密码
    public static final String USER_ChangeTradePassword= "User/ChangeTradePassword";
    //交易密码是否设置
    public static final String USER_ISTRADEPASSWORDSET= "User/IsTradePasswordSet";
    //设置交易密码
    public static final String USER_SETTRADEPASSWORD= "User/SetTradePassword";
    //发送登陆后的所有短信码
    public static final String NOTIFY_SENDBYUSER= "Notify/SendByUser";
    //重置登录密码
    public static final String USER_SETLOGINPASSWORDBYSMS= "User/SetLoginPasswordBySMS";
    //重置交易密码
    public static final String USER_SETTRADEPASSWORDBYSMS= "User/SetTradePasswordBySMS";
    //获取地址及余额
    public static final String USER_BALANCE= "User/Balance";
    //查询地址是否存在
    public static final String OPEN_CHECKBLOCKADDRESS= "Open/CheckBlockAddress";
    //币种类型获取
    public static final String OPEN_SUPPORTCOINCONFIG= "Open/SupportCoinConfig";
    //币种兑换配置获取
    public static final String OPEN_EXCHANGECOINCONFIG= "Open/ExchangeCoinConfig";
    //兑换货币
    public static final String POCKETSERVICE_EXCHANGE= "PocketService/Exchange";
    //获取兑换历史列表
    public static final String POCKETSERVICE_TAKEEXCHANGERECORD= "PocketService/TakeExchangeRecord";
    //转账
    public static final String POCKETSERVICE_TRANSFER= "PocketService/Transfer";
    //转入历史记录
    public static final String POCKETSERVICE_TAKETRANSFERINRECORD= "PocketService/TakeTransferInRecord";
    //转出历史记录
    public static final String POCKETSERVICE_TAKETRANSFEROUTRECORD= "PocketService/TakeTransferOutRecord";
    //账单
    public static final String POCKETSERVICE_TAKEJOURNALBYINDEX= "PocketService/TakeJournalByIndex";
    //账单详情
    public static final String POCKETSERVICE_TAKEJOURNALINFO= "PocketService/TakeJournalInfo";
    //验证更换手机
    public static final String USER_VALIDATERESTPHONENUMBERCODE= "User/ValidateRestPhoneNumberCode";
    //更换手机号码
    public static final String USER_REBANDPHONE= "User/RebandPhone";
    //token刷新
    public static final String USER_REFRESHTOKEN= "User/RefreshToken";
    //版本更新
    public static final String OPEN_VERSION= "Open/Version";
    //分享红包
    public static final String POCKETSERVICE_SHAREREDPACKETBYLINK= "PocketService/ShareRedPacketByLink";

    //红包详情
    public static final String POCKETSERVICE_TAKEREDPACKETINFO= "PocketService/TakeRedPacketInfo";

    //币种价格
    public static final String OPEN_REALTIMEMARKET= "Open/RealTimeMarket";





















}
