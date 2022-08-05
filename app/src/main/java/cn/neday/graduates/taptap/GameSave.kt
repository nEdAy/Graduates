package cn.neday.graduates.taptap

import androidx.annotation.NonNull
import cn.leancloud.types.LCNull
import com.tapsdk.bootstrap.gamesave.TapGameSave
import com.tencent.mmkv.MMKV
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.*

/**
 * TapTap 云存档管理
 * https://developer.taptap.com/docs/sdk/gamesaves/guide/
 * 字段含义	字段名	是否可选	说明
 * 存档 ID	objectId	自动生成	云存档云端为游戏存档生成的唯一字符串。使用此 ID 来引用你游戏客户端中保存的游戏。
 * 关联用户	user	自动获取	云存档 SDK 自动获取当前用户信息做绑定。
 * 存档原文件	gameFile	必须	开发者提供的存档原文件
 * 存档名称	name	必须	开发者为游戏存档提供的简称，存档名称不对玩家展示。特殊情况下存档名称可以自定规则来完成「存档组」的概念。
 * 存档描述	summary	必须	长度不超过 1000 的字符串。开发者提供已保存游戏存档的描述，这往往也作为展示给玩家的实际存档名。
 * 修改时间	modifiedAt	必须	存档原文件的修改时间或添加时间。
 * 游戏时长	playedTime	可选	开发者提供存档的游戏时间（以毫秒为单位）。
 * 游戏进度	progressValue	可选	开发者提供的游戏进度信息（整数），例如当前进度是第几关。
 * 存档封面	cover	可选	开发者提供的图片，目前 SDK 只允许上传 PNG / JPG 格式的图片。
 */
object GameSave {
    private const val mmapID = "score"
    fun save() {
        val snapshot = TapGameSave()
        snapshot.name = "internal name"
        snapshot.summary = "description"
        snapshot.playedTime = 60000.0 // ms
        snapshot.progressValue = 100
        //snapshot.setCover(image_local_path) // jpg/png
        snapshot.setGameFile(MMKV.getRootDir() + "/" + mmapID)
        snapshot.modifiedAt = Date()
        snapshot.saveInBackground().subscribe(object : Observer<TapGameSave> {
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(gameSave: TapGameSave) {
                println("存档保存成功：" + gameSave.toJSONString())
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onComplete() {}
        })
    }

    fun load() {
        TapGameSave.getCurrentUserGameSaves()
            .subscribe(object : Observer<List<TapGameSave>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(tapGameSaves: List<TapGameSave>) {
                    for (gameSave in tapGameSaves) {
                        val summary = gameSave.summary
                        val modifiedAt: Date = gameSave.modifiedAt
                        val playedTime = gameSave.playedTime
                        val progressValue = gameSave.progressValue
                        val cover = gameSave.cover
                        val gameFile = gameSave.gameFile
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {}
            })
    }

    fun find() {
        val gameSaveQuery = TapGameSave.getQueryWithUser()
        gameSaveQuery.whereGreaterThan("progressValue", 3)
        gameSaveQuery.findInBackground().subscribe(object : Observer<List<TapGameSave>> {
            override fun onSubscribe(disposable: Disposable) {}
            override fun onNext(gamesaves: List<TapGameSave>) {
                // gamesaves 是包含满足条件的云存档对象的数组
            }

            override fun onError(throwable: Throwable) {}
            override fun onComplete() {}
        })
    }

    fun delete() {
        val gameSave = TapGameSave()
        gameSave.deleteInBackground().subscribe(object : Observer<LCNull> {
            override fun onSubscribe(@NonNull d: Disposable) {}
            override fun onNext(response: LCNull) {
                // Deleted.
            }

            override fun onError(@NonNull e: Throwable) {
                println("Failed to delete:" + e.message)
            }

            override fun onComplete() {}
        })
    }
}