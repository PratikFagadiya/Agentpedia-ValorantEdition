package com.pratiik.valorantagentscompose

import android.app.Application
import androidx.annotation.Keep
import dagger.hilt.android.HiltAndroidApp

@Keep
@HiltAndroidApp
class BaseApplication : Application()