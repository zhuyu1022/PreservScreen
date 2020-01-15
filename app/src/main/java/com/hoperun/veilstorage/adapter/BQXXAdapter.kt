package com.hoperun.veilstorage.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hoperun.veilstorage.R
import com.hoperun.veilstorage.entity.MsckEntity

class BQXXAdapter : BaseQuickAdapter<MsckEntity, BaseViewHolder>(R.layout.item_msck_layout) {
    override fun convert(helper: BaseViewHolder, item: MsckEntity?) {

       // helper.setText(R.id.weightTv, item?.WEIGHT.toString())
    }
}