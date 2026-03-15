package com.example.network.module

import com.example.network.module.albums.Album
import com.example.network.module.albums.Artists
import com.example.network.module.albums.CoverArt
import com.example.network.module.albums.Date
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.serializer

object AlbumDeserializer: KSerializer<Album> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        serialName = Album::class.simpleName!!
    ){
        element<Artists?>("artists")
        element<List<CoverArt>?>("coverArt")
        element<String?>("date")
        element<String?>("id")
        element<String?>("name")
        element<String?>("type")
        element<String?>("uri")
    }


    override fun deserialize(decoder: Decoder): Album = decoder.decodeStructure(descriptor){
        var artists: Artists? = null
        var coverArt: List<CoverArt>? = null
        var date: String? = null
        var id: String? = null
        var name: String? = null
        var type: String? = null
        var uri: String? = null

        while (true){
            when(val index = decodeElementIndex(descriptor)){

                0 ->{
                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
                        "This serializer only works with JSON"
                    )

                    val element = jsonDecoder.decodeJsonElement()

                    artists = decoder.json.decodeFromJsonElement(
                        element =element,
                        deserializer = Artists.serializer()
                    )
                }
                1 ->{
                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
                        "This serializer only works with JSON"
                    )

                    val element = jsonDecoder.decodeJsonElement()

                    coverArt = decoder.json.decodeFromJsonElement(
                        element = element,
                        deserializer = serializer<List<CoverArt>>()
                    )

                }
                2 ->{
                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
                        "This serializer only works with JSON"
                    )

                    val element = jsonDecoder.decodeJsonElement()

                    date = if(element is JsonObject){
                        decoder.json.decodeFromJsonElement(
                            element = element,
                            deserializer = Date.serializer()
                        ).year.toString().substring(0..3)
                    } else if(element is JsonPrimitive && element.isString){
                        element.content
                    } else null
                }
                3 ->{
                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
                        "This serializer only works with JSON"
                    )

                    val element = jsonDecoder.decodeJsonElement()

                    id = decoder.json.decodeFromJsonElement(
                        element =element,
                        deserializer = String.serializer()
                    )
                }
                4 ->{
                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
                        "This serializer only works with JSON"
                    )

                    val element = jsonDecoder.decodeJsonElement()

                    name = decoder.json.decodeFromJsonElement(
                        element =element,
                        deserializer = String.serializer()
                    )
                }
                5 ->{
                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
                        "This serializer only works with JSON"
                    )

                    val element = jsonDecoder.decodeJsonElement()

                    type = decoder.json.decodeFromJsonElement(
                        element =element,
                        deserializer = String.serializer()
                    )
                }
                6 ->{
                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
                        "This serializer only works with JSON"
                    )

                    val element = jsonDecoder.decodeJsonElement()

                    uri = decoder.json.decodeFromJsonElement(
                        element =element,
                        deserializer = String.serializer()
                    )
                }
                CompositeDecoder.DECODE_DONE -> break
                else -> throw SerializationException("Invalid index")
            }
        }

        return@decodeStructure Album(
            artists!!,
            coverArt!!,
            date!!,
            id!!,
            name!!,
            type!!,
            uri!!
        )
    }

    override fun serialize(encoder: Encoder, value: Album) = encoder.encodeStructure(
        descriptor
    ) {
        encodeStringElement(descriptor, 0, value.artists.toString())
    }

}