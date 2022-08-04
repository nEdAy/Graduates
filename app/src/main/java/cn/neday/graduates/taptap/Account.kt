package cn.neday.graduates.taptap

import com.dylanc.longan.topActivity
import com.tapsdk.bootstrap.Callback
import com.tapsdk.bootstrap.account.TDSUser
import com.tapsdk.bootstrap.exceptions.TapError
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * TapTap 内建账户管理
 * https://developer.taptap.com/docs/sdk/authentication/guide/
 */
object Account {
    /**
     * 这里的「游客账户」可以保证玩家在同一个设备上多次登录都得到同一个账户，但是如果玩家卸载游戏重装之后再以「游客」身份登录则无法保证账户的唯一性
     */
    fun logInAnonymously() {
        TDSUser.logInAnonymously().subscribe(object : Observer<TDSUser> {
            override fun onSubscribe(disposable: Disposable) {}
            override fun onNext(resultUser: TDSUser) {
                // 登录成功，得到一个账户实例
                val userId = resultUser.objectId
            }

            override fun onError(throwable: Throwable) {}
            override fun onComplete() {}
        })
    }

    fun getCurrentUser(): TDSUser? {
        return TDSUser.getCurrentUser()
    }

    fun login() {
        TDSUser.loginWithTapTap(topActivity, object : Callback<TDSUser> {
            override fun onSuccess(resultUser: TDSUser) {
                // 开发者可以调用 resultUser 的方法获取更多属性。
                val userId = resultUser.objectId // 用户唯一标识
                val userName = resultUser.username
                val avatar = resultUser["avatar"] as String // 头像
                val nickName = resultUser["nickname"] as String // 昵称

                //                            Map<String,Object> authData = (Map<String,Object>)resultUser.get("authData");
//                            Map<String,Object> taptapAuthData = (Map<String, Object>) authData.get("taptap");
//                            Log.d(TAG,"authData:"+ JSON.toJSONString(authData));
//                            Map<String, Object> authDataResult = (Map<String, Object>) ((Map<String, Object>) resultUser.get("authData")).get("taptap");
//                            Log.d(TAG, "unionid:"+taptapAuthData.get("unionid").toString());
//                            Log.d(TAG, "openid:"+taptapAuthData.get("openid").toString());
            }

            override fun onFail(error: TapError) {

            }
        }, "public_profile")
    }

    fun logout() {
        TDSUser.logOut()
        // currentUser 变为 null
        val currentUser = TDSUser.getCurrentUser()
    }
}