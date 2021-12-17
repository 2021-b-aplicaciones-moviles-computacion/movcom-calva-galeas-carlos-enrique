import Receta
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import Ingrediente
import java.lang.Exception

fun main() {
    //Creamos las entidades, si ya están creadas simplemente te indica que el archivo ya existe
    val archivo1 = Archivo("C:\\Users\\CARLOS\\Desktop\\Moviles\\movcom-calva-galeas-carlos-enrique\\02-Deberes\\Receta-ingrediente\\src\\Archivos\\Recetas.txt")
    val archivo2 = Archivo("C:\\Users\\CARLOS\\Desktop\\Moviles\\movcom-calva-galeas-carlos-enrique\\02-Deberes\\Receta-ingrediente\\src\\Archivos\\Ingredientes.txt")
    menu(archivo1, archivo2)
}

fun menu(archivo1: Archivo, archivo2: Archivo) {
    println(
        "=================== MENÚ PRINCIPAL ===========================\n" +
                "1.Menú de Recetas\n" +
                "2.Menú de Ingredientes\n" +
                "3.Salir del Sistema\n" +
                "Elija la opción:"
    )
    when (validarEntero()) {
        1 -> {
            println( "======= MENÚ DE RECETAS ==========\n" +
                        "1. Registrar receta\n" +
                        "2. Actualizar receta\n" +
                        "3. Eliminar receta\n" +
                        "4. Leer recetas\n" +
                        "5. Regresar\n" +
                        "Elija la opción:"
            )
            when (validarEntero()) {
                1 -> {
                    val receta= Receta()
                    receta.registrarReceta(archivo1)
                    menu(archivo1, archivo2)
                }
                2 -> {
                    println("Ingrese el nombre de la receta:")
                    val receta = validarTexto()
                    if (archivo1.buscarRegistro(receta)) {
                        println(
                                    "1.Nombre\n" +
                                    "2.Tipo de receta\n" +
                                    "3.Número de ingredientes \n" +
                                    "4.Precio\n"+
                                    "5.Tiempo de preparación(minutos) \n" +
                                    "Elija el dato a actualizar :"
                        )
                        val indice = validarEntero()
                        var cambio = ""
                        when (indice) {
                            1 -> {
                                println("Ingrese el nombre de la receta:")
                                cambio = validarTexto()

                            }
                            2 -> {
                                println("Tipo de receta:")
                                cambio = validarTexto()

                            }
                            3 -> {
                                println("Número de ingredientes:")
                                cambio = validarEntero().toString()

                            }
                            4 -> {
                                println("Precio:")
                                cambio = validarDouble().toString()
                            }
                            5-> {
                                println(
                                    "Tiempo de preparación (minutos):"
                                )
                                cambio = validarEntero(1000).toString()
                            }
                            else -> {
                                println("Opción no válida")
                                menu(archivo1, archivo2)
                            }
                        }

                        archivo1.actualizarArchivo(indice - 1, receta, cambio)
                        println("Receta actualizada con éxito")
                        menu(archivo1, archivo2)
                    } else {
                        println("Receta no registrada")
                        menu(archivo1, archivo2)
                    }

                }
                3 -> {
                    println("Ingrese nombre de la receta:")
                    val receta = validarTexto()
                    if (archivo1.buscarRegistro(receta)) {
                        archivo1.eliminarRegistro(receta)
                        println("Receta eliminada exitósamente")
                        menu(archivo1, archivo2)
                    } else {
                        println("Receta no registrada")
                        menu(archivo1, archivo2)
                    }
                }
                4 -> {
                    println("-------------------------- LISTA DE RECETAS -------------------------------------")
                    println("Nombre | Tipo de receta | Numero de ingredientes  | Precio | Tiempo de preparación (minutos)")
                    archivo1.leerArchivos()
                    menu(archivo1, archivo2)
                }
                5 -> { menu(archivo1, archivo2)}
                else -> {
                    println("Opción no válida")
                    menu(archivo1, archivo2)
                }
            }
        }
        2 -> {
            println("======= MENÚ DE INGREDIENTES ==========\n" +
                        "1. Registrar ingrediente\n" +
                        "2. Actualizar ingrediente \n" +
                        "3. Eliminar ingrediente \n" +
                        "4. Leer ingredientes\n" +
                        "5. Regresar\n" +
                        "Elija la opción:"
            )
            when (validarEntero()) {
                1 -> {
                    val ingrediente = Ingrediente(archivo1,archivo2)
                    ingrediente.registrarIngrediente(archivo2)
                    menu(archivo1, archivo2)
                }
                2 -> {
                    println("Ingrese el nombre del ingrediente:")
                    val ingrediente = validarTexto()
                    if (archivo2.buscarRegistro(ingrediente)) {
                        println(
                                    "1.Nombre del ingrediente\n" +
                                    "2.Nombre de la receta\n" +
                                    "3.Fecha de compra\n" +
                                    "4.Inventario \n" +
                                    "5.Precio Unitario:\n" +
                                    "Elija el dato a actualizar:"
                        )
                        val indice = validarEntero()
                        var cambio = ""
                        when (indice) {
                            1 -> {
                                println("Ingrese el nombre del ingrediente:")
                                cambio = validarTexto()
                            }
                            2 -> {
                                println("Ingrese nombre de la receta:")
                                cambio = validarTexto()
                                if (!archivo1.buscarRegistro(cambio)) {
                                    println(
                                        "Receta no registrada, desea registrarla?\n" +
                                                "1.Sí\n" +
                                                "2.No"
                                    )
                                    when (validarEntero()) {
                                        1 -> {
                                            val receta= Receta()
                                            receta.registrarReceta(archivo1)
                                        }
                                        2 -> {
                                            menu(archivo1, archivo2)
                                        }
                                        else -> {
                                            println("Opción no válida")
                                            menu(archivo1, archivo2)
                                        }
                                    }

                                }
                            }
                            3 -> {
                                println("Fecha de compra:")
                                cambio = validarEntero(2021).toString()
                            }

                            4 -> {
                                println(
                                    "Inventario\n" +
                                            "1. Sí\n" +
                                            "2. No\n" +
                                            "Seleccione:"
                                )
                                cambio = validarBooleano().toString()
                            }
                            5 -> {
                                println("Precio Unitario:")
                                cambio = validarDouble().toString()
                            }
                            else -> {
                                println("Opción no válida")
                                menu(archivo1, archivo2)
                            }
                        }
                        archivo2.actualizarArchivo(indice - 1, ingrediente, cambio)
                        println("Ingrediente actualizado con éxito")
                        menu(archivo1, archivo2)
                    } else {
                        println("Ingrediente no registrado")
                        menu(archivo1, archivo2)
                    }

                }
                3 -> {
                    println("Ingrese nombre del ingrediente:")
                    val ingrediente = validarTexto()
                    if (archivo2.buscarRegistro(ingrediente)) {
                        archivo2.eliminarRegistro(ingrediente)
                        println("Ingrediente eliminado exitósamente")
                        menu(archivo1, archivo2)
                    } else {
                        println("Ingrediente no registrado")
                        menu(archivo1, archivo2)
                    }
                }
                4 -> {
                    println("------------------------Lista de Ingredientes --------------------------------------")
                    println("Nombre del ingrediente | Nombre de la receta | Fecha de compra | Inventario | Precio Unitario")
                    archivo2.leerArchivos()
                    menu(archivo1, archivo2)
                }
                5 -> { menu(archivo1, archivo2)}
                else -> {
                    println("Opción no válida")
                    menu(archivo1, archivo2)
                }
            }
        }
        3 -> {
            println("Fin del sistema")
        }
        else -> {
            println("Opción no válida, seleccione nuevamente")
            menu(archivo1, archivo2)
        }
    }
}

fun validarBooleano(): Boolean {
    when (validarEntero()) {
        1 -> {
            return true
        }
        2 -> {
            return false
        }
        else -> {
            println("Opción no válida")
            return validarBooleano()
        }
    }
}

fun ingresarDate(): Date {
    println("Ingrese fecha en formato dd/MM/yyyy:")
    val dateFormat = SimpleDateFormat("dd/MM/yyyy") //Formato de ingreso
    val date = ingresoPorTeclado().nextLine()
    dateFormat.isLenient = false //evito que fechas inválidas ( más de 30 días o 12 meses) sean aceptadas
    return try {
        val dateIngresado = dateFormat.parse(date)
        if (dateIngresado.before(Date.from(Instant.now()))) {//Fechas deben ser antes de fecha actual
            dateIngresado
        } else {
            println("Fecha inválida")
            ingresarDate()
        }
        return dateFormat.parse(date)
    } catch (e: ParseException) {
        println("Formato no válido")
        ingresarDate() //Recursividad hasta recibir fecha válida
    }
}

fun ingresoPorTeclado(): Scanner { //Ingreso teclado
    return Scanner(System.`in`)
}

fun validarEntero(limite: Int = 1000): Int {
    return try {
        val num = ingresoPorTeclado().nextLine().toInt() //Verifica número entero
        if (num in 1 until limite) {
            num
        } else {
            println("Ingrese nuevamente")
            validarEntero(limite)
        }
    } catch (e: Exception) {
        println("Ingrese un valor entero mayor a cero")
        validarEntero()
    }

}

fun validarDouble(): Double {
    return try {
        val num = ingresoPorTeclado().nextLine().toDouble() //Verifica número double
        if (num > 0) num else validarDouble()
    } catch (e: Exception) {
        println("Ingrese un valor decimal mayor a cero")
        validarDouble()
    }
}

fun validarTexto(): String {
    val ingreso = ingresoPorTeclado().nextLine()
    return if (ingreso.matches("^[\\p{L} .'-]+$".toRegex())) {
        ingreso
    } else {
        println("Ingrese solo texto")
        validarTexto()
    }
}

