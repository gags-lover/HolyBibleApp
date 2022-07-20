package com.github.astat1cc.holybibleapp.core

import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception

class AbstractTest {

    @Test
    fun test_success() {
        val dataObject = DataObjectTest.Success("a", "b")
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        assertTrue(domainObject is DomainObjectTest.Success)
    }

    @Test
    fun test_fail() {
        val dataObject = DataObjectTest.Fail(Exception())
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        assertTrue(domainObject is DomainObjectTest.Fail)
    }

    private sealed class DataObjectTest : Abstract.Object<DomainObjectTest, DataToDomainMapper>() {

        abstract override fun map(mapper: DataToDomainMapper): DomainObjectTest

        class Success(
            private val textOne: String,
            private val textTwo: String
        ) : DataObjectTest() {

            override fun map(mapper: DataToDomainMapper) = mapper.map(textOne, textTwo)
        }

        class Fail(private val e: Exception) : DataObjectTest() {

            override fun map(mapper: DataToDomainMapper) = mapper.map(e)
        }
    }

    private interface DataToDomainMapper : Abstract.Mapper {

        fun map(textOne: String, textTwo: String): DomainObjectTest

        fun map(e: Exception): DomainObjectTest

        class Base : DataToDomainMapper {

            override fun map(textOne: String, textTwo: String) =
                DomainObjectTest.Success("$textOne $textTwo")

            override fun map(e: Exception): DomainObjectTest =
                DomainObjectTest.Fail(e)
        }
    }

    private sealed class DomainObjectTest : Abstract.Object<UiObjectTest, DomainToUiMapper>() {

        abstract override fun map(mapper: DomainToUiMapper): UiObjectTest

        class Success(private val concatenated: String) : DomainObjectTest() {

            override fun map(mapper: DomainToUiMapper) = mapper.map(concatenated)
        }

        class Fail(private val e: Exception) : DomainObjectTest() {

            override fun map(mapper: DomainToUiMapper) = mapper.map(e)
        }
    }

    private interface DomainToUiMapper : Abstract.Mapper {

        fun map(concatenated: String): UiObjectTest

        fun map(e: Exception): UiObjectTest
    }

    private sealed class UiObjectTest : Abstract.Object<Unit, Abstract.Mapper.Empty>()
}