@file:Suppress("unused")

package cn.neday.graduates.repository.serialize

import android.content.Context
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

class MMKVInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    MMKV.initialize(context)
  }

  override fun dependencies() = emptyList<Class<Initializer<*>>>()
}
