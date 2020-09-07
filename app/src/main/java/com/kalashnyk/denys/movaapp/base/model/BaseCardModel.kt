package com.kalashnyk.denys.movaapp.base.model

import androidx.databinding.BaseObservable

abstract class BaseCardModel : BaseObservable() {
    abstract fun getCardId(): String
    abstract fun getCardType(): String
    abstract fun getBaseModel(): BaseModel
}