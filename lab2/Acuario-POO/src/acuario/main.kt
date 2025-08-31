package acuario

/* fun construirAcuario() {
    val miAcuario = Acuario(ancho = 25, largo = 25, alto = 40)
    miAcuario.imprimirTamano()

    val miTorre = TanqueTorre(diametro = 25, alto = 45)
    miTorre.imprimirTamano()
}
*/
fun CrearPeces(){
    val tiburon = Tiburon()
    val payazo = PezPayaso()

    println("Color del tiburon ${tiburon.color}")
    println("Color del pez payazo ${payazo.color}")
    tiburon.comer()
    payazo.comer()
}
fun main() {
    CrearPeces()
}
