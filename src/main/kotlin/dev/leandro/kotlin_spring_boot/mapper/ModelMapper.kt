package dev.leandro.kotlin_spring_boot.mapper

import org.modelmapper.ModelMapper

object ModelMapper {

//    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()
    private val mapper: ModelMapper = ModelMapper()

    fun <O,D> parseObject(origin: O, destination: Class<D>?): D {
        return mapper.map(origin, destination)
    }

    fun <O,D> parseListObjects(origin: List<O>, destination: Class<D>?): ArrayList<D> {
        val destinationObjects: ArrayList<D> = ArrayList()
        for(o in origin) {
            destinationObjects.add(mapper.map(o, destination))
        }
        return destinationObjects
    }
}