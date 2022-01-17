import Receta
import java.util.*

class Ingrediente (archivo1: Archivo, archivo2: Archivo) {
    val separador = "|"
    val saltoLinea = "\n"
    val nombre: String
    val nombreReceta: String
    val fechaCompra: Date
    val inventario: Boolean
    val precioUnitario: Double

    init {
        println("Ingrese el nombre del ingrediente:")
        nombre = validarTexto()
        println("Ingrese el nombre de la receta en la que se usará el ingrediente:")
        nombreReceta = validarTexto()
        if (!archivo1.buscarRegistro(nombreReceta)) {
            println(
                "Receta no registrada, desea registrarla?\n" +
                        "1.Sí\n" +
                        "2.No,Cancelar"
            )
            when (validarEntero()) {
                1 -> {
                    val registro = Receta()
                    registro.registrarReceta(archivo1)
                }
                2 -> {
                    println("Cancelando registro")
                    menu(archivo1, archivo2)
                }
                else -> {
                    println("Opción no válida, registre nuevamente")
                    registrarIngrediente(archivo2)
                }
            }

        }
        println("Fecha de compra del ingrediente:")
        fechaCompra = ingresarDate()
        println(
            "Hay existencias en el inventario \n" +
                    "1. Sí\n" +
                    "2. No\n" +
                    "Seleccione:"
        )
        inventario = validarBooleano()
        println("Precio unitario del ingrediente:")
        precioUnitario = validarDouble()
    }

    fun registrarIngrediente(archivo2: Archivo) {
        //Verificamos si el libro no ha sido registrado previamente
        if (!archivo2.buscarRegistro(nombre)) {
            //Guardamos en el archivo
            archivo2.escritorArchivo("$nombre$separador$nombreReceta$separador$fechaCompra$separador$inventario$separador$precioUnitario$saltoLinea")
            println("Ingrediente registrado exitósamente")
        } else {
            println("El ingrediente ya está registrado")
        }
    }
}