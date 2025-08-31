package tienda

open class Producto (
    open var nombre : String,
    open var precio : Double,
    open var stock : Int,
    ){

    open val descripcion = "Producto genérico"

    open val ValorTotal : Double
    get() = precio * stock

    open fun imprimirInfo(){
        println("Nombre: $nombre")
        println("Precio: $precio")
        println("Stock: $stock")
        println("Valor Total: $ValorTotal")
        println("Descripción: $descripcion\n")
    }

    constructor(nombre: String, precio: Double) : this(nombre, precio, 10) { }
}