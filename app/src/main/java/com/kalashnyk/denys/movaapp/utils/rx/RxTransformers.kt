package com.kalashnyk.denys.movaapp.utils.rx

import com.kalashnyk.denys.movaapp.utils.settings.DEFAULT_TIMEOUT
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxTransformers {

    fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
    }

}