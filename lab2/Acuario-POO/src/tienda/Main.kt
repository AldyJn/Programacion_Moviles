package tienda

fun main() {
    println("=== Sistema de Productos ===\n")

    val prod1 = Producto("Camiseta", 50.0, 20)
    val prod2 = Producto("Pantalón", 80.0) // stock por defecto 10

    prod1.imprimirInfo()
    prod2.imprimirInfo()

    val prodEsp1 = ProductoEspecial("Reloj inteligente", 350.0, 5, "Electrónica")
    val prodEsp2 = ProductoEspecial("Zapatillas edición limitada", 200.0, 3, "Calzado")

    prodEsp1.imprimirInfo()
    prodEsp2.imprimirInfo()

    prodEsp1.vender(2)
    prodEsp1.reponer(5)
    prodEsp1.vender(10)

    prodEsp2.vender(1)
}
