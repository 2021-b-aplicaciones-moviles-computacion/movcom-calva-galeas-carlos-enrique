import java.util.*

fun main() {
    println("Hola mundo"); // ; -> no es requerido
    // Tipo nombre = valor;
    // Int edad = 12;

    // Tipos de variables

    // INMUTABLES (val)

    val inmutable: String = "Adrian"
    // inmutable = "Vicente"; // X

    // MUTABLES (var)
    var mutable: String = "Vicente"
    mutable = "Adrian";

    // val > var

    // Sintaxis y Duck Typing

    val ejemploVariable = "Nombre"
    var edadEjemplo: Int = 12
    // edadEjemplo = 14.2

    // Tipos de variables JAVA

    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()

    // Condicionales

    if (true) {
        // Verdadera
    } else {
        // Falso
    }

    // switch Estado -> S -> C -> :::::
    val estadoCivilWhen: String = "S"

    when (estadoCivilWhen) {
        ("S") -> {
            println("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"
    // condicion ? bloque-true : bloque-false

//    imprimirNombre("Adrian")
//    calcularSueldo(100.00)
//    calcularSueldo(100.00, 14.00)
    calcularSueldo(100.00, 14.00, 25.00)

    // Named Parameters
    calcularSueldo(
        bonoEspecial = 15.00,
        // tasa = 12.00
        sueldo = 150.00,
    )

    calcularSueldo(
        tasa = 14.00,
        bonoEspecial = 30.00,
        sueldo = 1000.00
    )


    // Tipos de Arreglos

    // Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

    // Arreglo Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // OPERADORES -> Sirven para los arreglos estáticos y dinámicos


    // FOR EACH -> Unit
    // Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloDinamico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }
//        .map { valorActual: Int ->
//            return@map valorActual + 15
//        }

    println(respuestaMapDos)



    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 // Expresion Condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)


    // OR AND
    // OR ->  ANY (Alguno cumple?)
    // AND ->  ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // false

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println(respuestaReduce) // 78

    // 100
    // [12, 15, 8, 10]
    val arregloDanio = arrayListOf<Int>(12, 15, 8, 10)
    val respuestaReduceFold = arregloDanio
        .fold(
            100, // acumulado inicial
            { acumulado, valorActualIteracion ->
                return@fold acumulado - valorActualIteracion
            }
        )
    println(respuestaReduceFold)

    val vidaActual: Double = arregloDinamico
        .map { it * 2.3 } // arreglo
        .filter { it > 20 } // arreglo
        .fold(100.00, { acc, i -> acc - i }) // valor
        .also { println(it) } // ejecutar codigo extra
    println("Valor vida actual ${vidaActual}") // 3.4


    // CLASES
    val ejemploUno = Suma(1, 2)
    val ejemploDos = Suma(null, 2)
    val ejemploTres = Suma(1, null)
    val ejemploCuatro = Suma(null, null)

    println(ejemploUno.sumar())
    println(Suma.historialSumas)
    println(ejemploDos.sumar())
    println(Suma.historialSumas)
    println(ejemploTres.sumar())
    println(Suma.historialSumas)
    println(ejemploCuatro.sumar())
    println(Suma.pi)
    println(Suma.historialSumas)
}

// void imprimirNombre (String nombre){}
fun imprimirNombre(nombre: String): Unit { // Unit es el void, y es opcional
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, // Requerido
    tasa: Double = 12.00, // Opcional (Defecto)
    bonoEspecial: Double? = null, // Opcional (Null) -> nullable
): Double {
    // String -> String?
    // Int -> Int?
    // Date -> Date?
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}



abstract class NumerosJava {
    protected val numeroUno: Int // Propiedad clase
    private val numeroDos: Int // Propiedad clase

    constructor(
        uno: Int,   // Parametros requeridos
        dos: Int,   // Parametros requeridos
    ) {
//        this.numeroUno = uno
//        this.numeroDos = dos
        numeroUno = uno
        numeroDos = dos
        println("Inicializar")
    }

}


abstract class Numeros(
    // Constructor Primario
    protected var numeroUno: Int, // Propiedad clase
    protected var numeroDos: Int,  // Propiedad clase
) {
    init { // Bloque inicio del constructor primario
        println("Inicializar")
    }
}
// instancia.numeroUno
// instancia.numeroDos


class Suma(
    // Constructor primario
    uno: Int, // Parametro requerido
    dos: Int, // Parametro requerido
): Numeros( // Constructor "papa" (super)
    uno,
    dos
) {
    init { // Es el bloque de codigo del constructor primario
        this.numeroUno
        this.numeroDos
        // X -> this.uno -> NO EXISTEN
        // X -> this.dos -> NO EXISTEN
    }

    constructor( //  Segundo constructor
        uno: Int?, // parametros
        dos: Int // parametros
    ) : this( // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    ){
        // bloque codigo segundo constructor
    }

    constructor( //  Tercer constructor
        uno: Int, // parametros
        dos: Int? // parametros
    ) : this(
        // llamada constructor primario
        uno,
        if (dos == null) 0 else dos,
    ){
        // bloque codigo tercer constructor
    }

    constructor( //  Cuarto constructor
        uno: Int?, // parametros
        dos: Int? // parametros
    ) : this(
        // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    ){
        // bloque codigo cuarto constructor
    }

    // public fun sumar(): Int {
    fun sumar(): Int {
        // val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    // Singleton (solo hay una sola instancia de estas cosas)
    companion object { // METODOS Y PROPIEDADES ESTATICAS
        val pi = 3.14
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }






}











