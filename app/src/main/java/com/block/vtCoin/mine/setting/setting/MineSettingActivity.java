package com.block.vtCoin.mine.setting.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.block.net.mvp.presenter.BasePresenter;
import com.block.vtCoin.R;
import com.block.vtCoin.constant.Constant;
import com.block.vtCoin.entity.SmsEntity;
import com.block.vtCoin.entity.UserInfoEntity;
import com.block.vtCoin.main.BaseActivity;
import com.block.vtCoin.login.LoginActivity;
import com.block.vtCoin.mine.setting.account_address.AccountAddressActivity;
import com.block.vtCoin.mine.setting.authentication.AuthenticationActivity;
import com.block.vtCoin.mine.setting.set_deal_password.SetDealPasswordActivity;
import com.block.vtCoin.mine.setting.email.BindEmailAddressActivity;
import com.block.vtCoin.mine.setting.google_auth.GoogleAuthActivity;
import com.block.vtCoin.mine.setting.setting.p.GetUserInfoPresenter;
import com.block.vtCoin.mine.setting.setting.p.LogoutPresenter;
import com.block.vtCoin.mine.setting.modify_password.ModifyPasswordActivity;
import com.block.vtCoin.mine.setting.phone_num.BindPhoneActivity;
import com.block.vtCoin.mine.setting.receipt_setting.ReceiptSettingActivity;
import com.block.vtCoin.mine.setting.second_login_verify.SecondLoginVerifyActivity;
import com.block.vtCoin.mine.setting.setting.v.GetUserInfoViewImpl;
import com.block.vtCoin.mine.setting.setting.v.LogoutViewImpl;
import com.block.vtCoin.mine.setting.trader_ad.TraderAdActivity;
import com.block.vtCoin.request.ApiAction;
import com.block.vtCoin.request.MyModel;
import com.block.vtCoin.util.SPUtils;
import com.block.vtCoin.widget.dialog.NormalDialog;
import com.zhy.android.percent.support.PercentRelativeLayout;
import java.util.HashMap;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Description  用户设置
 */
public class MineSettingActivity extends BaseActivity {
    @Bind(R.id.tv_deal_password_state)
    TextView tvDealPasswordState;
    @Bind(R.id.rl_deal_password)
    PercentRelativeLayout rlDealPassword;
    @Bind(R.id.tv_change_login_password)
    TextView tvChangeLoginPassword;
    @Bind(R.id.tv_phone_state)
    TextView tvPhoneState;
    @Bind(R.id.rl_phone_num)
    PercentRelativeLayout rlPhoneNum;
    @Bind(R.id.tv_email_state)
    TextView tvEmailState;
    @Bind(R.id.rl_email)
    PercentRelativeLayout rlEmail;
    @Bind(R.id.rl_login_verify_again)
    PercentRelativeLayout rlLoginVerifyAgain;
    @Bind(R.id.tv_two_login_state)
    TextView tvTwoLoginState;
    @Bind(R.id.tv_trader_ad)
    TextView tvTraderAd;
    @Bind(R.id.tv_account_address_manager)
    TextView tvAccountAddressManager;
    @Bind(R.id.tv_authentication_state)
    TextView tvAuthenticationState;
    @Bind(R.id.rl_authentication)
    PercentRelativeLayout rlAuthentication;
    @Bind(R.id.tv_google_attest_state)
    TextView tvGoogleAttestState;
    @Bind(R.id.rl_google_attest)
    PercentRelativeLayout rlGoogleAttest;
    @Bind(R.id.tv_set_gathering)
    TextView tvSetGathering;
    @Bind(R.id.tv_about_we)
    TextView tvAboutWe;
    @Bind(R.id.tv_exit)
    TextView tvExit;
    public static final int SETTING_DEAL_PAS = 1000;
    public static final int SETTING_TWO_LOGIN_VERIFY = 1000;
    private LogoutPresenter logoutPresenter;
    private GetUserInfoPresenter getUserInfoPresenter;
    private boolean isSetPassword, isAuthentication, isGoogle, isPhone, isEmail, isTwoGoogle, isTwoSms, isTwoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);
        setUltimateBar(R.color.blue1);
        intView();
    }

    private void intView() {
        getUserInfoPresenter.getUserInfo();
    }

    @Override
    public View getTitleBar() {
        return getNormalTitleBar().setTitle(getString(R.string.mine_setting));
    }

    @Override
    protected List<BasePresenter> getPresenter(List<BasePresenter> presenters) {
        getUserInfoPresenter = new GetUserInfoPresenter();
        getUserInfoPresenter.setModelView(new MyModel<UserInfoEntity>(), new GetUserInfoViewImpl(this, ApiAction.Account_GetUserInfo));
        logoutPresenter = new LogoutPresenter();
        logoutPresenter.setModelView(new MyModel<SmsEntity>(), new LogoutViewImpl(this, ApiAction.Account_Logout));
        presenters.add(getUserInfoPresenter);
        presenters.add(logoutPresenter);
        return presenters;
    }

    @OnClick({R.id.rl_deal_password, R.id.tv_change_login_password, R.id.rl_phone_num, R.id.rl_email, R.id.rl_login_verify_again, R.id.tv_trader_ad, R.id.tv_account_address_manager, R.id.rl_authentication, R.id.rl_google_attest, R.id.tv_set_gathering, R.id.tv_about_we, R.id.tv_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_deal_password:
                if (isSetPassword) {//已经设置了交易密码,去修改交易密码
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("from", 1);
                    toActivity(ModifyPasswordActivity.class, map);
                } else {
                    toActivityResult(SetDealPasswordActivity.class, SETTING_DEAL_PAS, null);
                }
                break;
            case R.id.tv_change_login_password:
                HashMap<String, Object> map = new HashMap<>();
                map.put("from", 0);
                toActivity(ModifyPasswordActivity.class, map);
                break;
            case R.id.rl_phone_num:
                toActivity(BindPhoneActivity.class);
                break;
            case R.id.rl_email:
                toActivity(BindEmailAddressActivity.class);
                break;
            case R.id.rl_login_verify_again:
                toActivity(SecondLoginVerifyActivity.class);
                toActivityResult(SecondLoginVerifyActivity.class,SETTING_TWO_LOGIN_VERIFY,null);
                break;
            case R.id.tv_trader_ad:
                toActivity(TraderAdActivity.class);
                break;
            case R.id.tv_account_address_manager:
                toActivity(AccountAddressActivity.class);
                break;
            case R.id.rl_authentication:
                toActivity(AuthenticationActivity.class);
                break;
            case R.id.rl_google_attest:
                toActivity(GoogleAuthActivity.class);
                break;
            case R.id.tv_set_gathering:
                toActivity(ReceiptSettingActivity.class);
                break;
            case R.id.tv_about_we:
                break;
            case R.id.tv_exit:
                showDialog();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_DEAL_PAS && resultCode == RESULT_OK) {//设置交易密码成功
            isSetPassword = true;
            SPUtils.putBoolean(Constant.IsSetPassword, isSetPassword);
            tvDealPasswordState.setText(getString(R.string.have_setting));
        }
    }

    public void showDialog() {
        NormalDialog dialog = new NormalDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutPresenter.logout();
            }
        });
        dialog.setMsg(getString(R.string.tip_logout));
        dialog.show();
    }

    /**
     * @param userInfo 拿到个人设置里的数据
     */
    public void setUserInfo(UserInfoEntity userInfo) {
        UserInfoEntity.DataBean data = userInfo.getData();
        /*是否设置交易密码*/
        isSetPassword = data.isAdvancedValid();
        isTwoLogin = data.isAgentQLN();
        isTwoGoogle = data.isTwoFactorGoogle();
        isTwoSms = data.isTwoFactorSMS();
        isPhone = data.isPhoneConfirm();
        isEmail = data.isEmainConfirm();

        isAuthentication = data.isPassIdentityConfirm();
        isGoogle = data.isGoogleConfirm();
        SPUtils.putBoolean(Constant.IsSetPassword, isSetPassword);
        SPUtils.putBoolean(Constant.IsTwoLoginConfirm, isTwoLogin);
        SPUtils.putBoolean(Constant.IsTowGoogleConfirm, isTwoGoogle);
        SPUtils.putBoolean(Constant.IsTowSmsConfirm, isTwoSms);
        SPUtils.putBoolean(Constant.IsBindPhone, isPhone);
        SPUtils.putBoolean(Constant.IsBindEmail, isEmail);

        if (isSetPassword) //是否设置交易密码
            tvDealPasswordState.setText(getString(R.string.have_setting));
        else
            tvDealPasswordState.setText(getString(R.string.have_not_setting));
        if (isPhone) //是否绑定手机号
            tvPhoneState.setText(data.getPhone());
        else
            tvPhoneState.setText(getString(R.string.unbind));
        if (isEmail) //是否绑定邮箱
            tvEmailState.setText(data.getEmail());
        else
            tvEmailState.setText(getString(R.string.unbind));
        if (isTwoLogin) //是开启二次登陆验证
            tvTwoLoginState.setText(getString(R.string.open));
        else
            tvTwoLoginState.setText(getString(R.string.close));
        if (isAuthentication)//是否通过了身份认证
            tvAuthenticationState.setText(getString(R.string.have_certified));
        else
            tvAuthenticationState.setText(getString(R.string.not_certified));
        if (isGoogle)//是否通过了谷歌认证
            tvGoogleAttestState.setText(getString(R.string.have_certified));
        else
            tvGoogleAttestState.setText(getString(R.string.not_certified));
    }

    public void logout() {
        toActivity(LoginActivity.class);
    }
}
