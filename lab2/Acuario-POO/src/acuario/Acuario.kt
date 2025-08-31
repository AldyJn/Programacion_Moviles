package acuario

open class Acuario(
    open var ancho: Int = 20,
    open var alto: Int = 40,
    open var largo: Int = 100
) {
    open var volumen: Int
        get() = ancho * alto * largo / 1000  // cm³ → litros
        set(valor) {
            alto = (valor * 1000) / (ancho * largo)
        }

    open val agua: Double
        get() = volumen * 0.9

    open val forma = "rectángulo"

    open fun imprimirTamano() {
        println("Forma: $forma")
        println("Ancho: $ancho cm  Largo: $largo cm  Alto: $alto cm")
        println("Volumen: $volumen l  Agua: $agua l (${agua / volumen * 100}% lleno)\n")
    }

    constructor(numeroDePeces: Int) : this() {
        val tanque = numeroDePeces * 2000 * 1.1
        alto = (tanque / (largo * ancho)).toInt()
    }
}
