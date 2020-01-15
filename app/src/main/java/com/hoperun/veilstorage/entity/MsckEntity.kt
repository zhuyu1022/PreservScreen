package com.hoperun.veilstorage.entity

import java.io.Serializable

class MsckEntity:Serializable{

    var SZQC: String = ""//	纱支全称
    var ORIPLACE: String = ""//供货单位
    var COLORNO: String = ""//色号
    var KWBZ: String = ""//库存备注
    var KWSM: String = ""//库存说明
    var BATCHNO: String = ""    //批号
    var KW: String = ""    //库位
    var WEIGHT: Double = 0.0//数量

}