package tienda

class ProductoEspecial(
    override var nombre: String,
    override var precio: Double,
    override var stock: Int,
    var categoria: String
) : Producto(nombre, precio, stock), AccionesProducto {

    override val descripcion = "Producto especial de categoría $categoria"

    override fun vender(cantidad: Int) {
        if (cantidad <= stock) {
            stock -= cantidad
            println("$cantidad unidades de $nombre vendidas.")
        } else {
            println("Stock insuficiente para $nombre. Solo hay $stock unidades.")
        }
    }

    override fun reponer(cantidad: Int) {
        stock += cantidad
        println("$cantidad unidades de $nombre repuestas. Stock actual: $stock")
    }

    override fun imprimirInfo() {
        super.imprimirInfo()
        println("Categoría: $categoria\n")
    }
}
