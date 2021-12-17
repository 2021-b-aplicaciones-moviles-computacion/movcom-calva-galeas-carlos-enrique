import java.util.*

class Receta {
    val separador = "|"
    val saltoLínea = "\n"
    val nombre: String
    val tipoReceta: String
    val numeroIngredientes: Int
    val precio: Double
    val tiempoPreparacion: Int

    init {
        println("Ingrese el nombre de la receta:")
        nombre = validarTexto()
        println(nombre)
        println("Ingrese el tipo de receta (Ej. Postre , sopa , etc):")
        tipoReceta = validarTexto()
        println("Ingrese el número de ingredientes:")
        numeroIngredientes = validarEntero()
        println("Ingrese el precio de la receta :")
        precio = validarDouble()
        println("Ingrese el tiempo de preparación de la receta (minutos) :")
        tiempoPreparacion = validarEntero()
    }

    fun registrarReceta(archivo1: Archivo) {
        //Verificamos si el autor no ha sido registrado previamente
        if (!archivo1.buscarRegistro(nombre)) {
            //Guardamos en el archivo
            archivo1.escritorArchivo("$nombre$separador$tipoReceta$separador$numeroIngredientes$separador$precio$separador$tiempoPreparacion$saltoLínea")
            println("Receta registrada exitósamente")
        } else {
            println("La receta ya está registrada")
        }
    }
}