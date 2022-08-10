package com.github.astat1cc.holybibleapp.data

interface TestamentTemp {

    fun save(testament: String)

    fun matches(testament: String): Boolean

    fun getTestamentType(): TestamentType

    class Base : TestamentTemp {

        private var temp = ""

        override fun save(testament: String) {
            temp = testament
        }

        override fun matches(testament: String) = temp == testament

        override fun getTestamentType() = temp.toTestamentType()

        private fun String.toTestamentType() =
            if (this == NAME_OF_OLD_TESTAMENT_FROM_CLOUD_RESPONSE) {
                TestamentType.OLD
            } else {
                TestamentType.NEW
            }
    }

    companion object {

        const val NAME_OF_OLD_TESTAMENT_FROM_CLOUD_RESPONSE = "OT"
    }
}