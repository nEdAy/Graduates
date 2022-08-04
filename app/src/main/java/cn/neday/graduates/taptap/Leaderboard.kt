package cn.neday.graduates.taptap

import cn.leancloud.*
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * TapTap 排行榜管理
 * https://developer.taptap.com/docs/sdk/leaderboard/guide/
 */
object Leaderboard {
    val score = "score"
    fun update() {
        val statistic: MutableMap<String, Double> = HashMap()
        statistic["score"] = 20.0
        LCLeaderboard.updateStatistic(Account.getCurrentUser(), statistic)
            .subscribe(object : Observer<LCStatisticResult> {
                override fun onSubscribe(disposable: Disposable) {}
                override fun onNext(jsonObject: LCStatisticResult) {
                    // scores saved
                }

                override fun onError(throwable: Throwable) {
                    // handle error
                }

                override fun onComplete() {}
            })
    }

    fun getResults() {
        val leaderboard = LCLeaderboard.createWithoutData("world")
        leaderboard.getResults(0, 10, null, null).subscribe(object : Observer<LCLeaderboardResult> {
            override fun onSubscribe(disposable: Disposable) {}
            override fun onNext(leaderboardResult: LCLeaderboardResult) {
                val rankings = leaderboardResult.results
                // process rankings
            }

            override fun onError(throwable: Throwable) {
                // handle error
            }

            override fun onComplete() {}
        })
    }

    fun update1() {
        val statistic: MutableMap<String, Double> = HashMap()
        statistic["score"] = 3458.0
        statistic["kills"] = 28.0
        LCLeaderboard.updateStatistic(Account.getCurrentUser(), statistic)
            .subscribe(object : Observer<LCStatisticResult> {
                override fun onSubscribe(disposable: Disposable) {}
                override fun onNext(jsonObject: LCStatisticResult) {
                    // saved
                }

                override fun onError(throwable: Throwable) {
                    // handle error
                }

                override fun onComplete() {}
            })
    }

    // LCLeaderboard.getUserStatistics(otherUser, Arrays.asList("world")).subscribe(/** 略 **/);
    fun getUserStatistics() {
        // 查询排行榜成员成绩
        var otherUser: LCUser? = null
        try {
            otherUser = LCUser.createWithoutData(LCUser::class.java, "5c76107144d90400536fc88b")
        } catch (e: LCException) {
            e.printStackTrace()
        }
        LCLeaderboard.getUserStatistics(otherUser).subscribe(object : Observer<LCStatisticResult> {
            override fun onSubscribe(disposable: Disposable) {}
            override fun onNext(lcStatisticResult: LCStatisticResult) {
                val statistics = lcStatisticResult.results
                for (statistic in statistics) {
                    println(statistic.name)
                    println(statistic.value.toString())
                }
            }

            override fun onError(throwable: Throwable) {
                // handle error
            }

            override fun onComplete() {}
        })
    }
}