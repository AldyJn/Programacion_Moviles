package acuario

interface AccionPez {
    fun comer()
}

abstract class Pez {
    abstract val color: String
}

class Tiburon : Pez(), AccionPez {
    override val color = "gris"

    override fun comer() {
        println("Tiburón: cazar y comer peces")
    }
}

class PezPayaso : Pez(), AccionPez {
    override val color = "dorado"

    override fun comer() {
        println("Pez payaso: comer algas")
    }
}
