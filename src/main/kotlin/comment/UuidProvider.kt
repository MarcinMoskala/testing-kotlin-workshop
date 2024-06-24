package domain.comment

interface UuidProvider {
    fun next(): String
}

class RealUuidProvider : UuidProvider {
    override fun next(): String = java.util.UUID.randomUUID().toString()
}

