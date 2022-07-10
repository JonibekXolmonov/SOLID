fun main() {
    val car = EnumCar.AUDI

    select(car)
}

fun select(car: EnumCar) = when (car) {
    EnumCar.AUDI -> println("audi")
    EnumCar.MERS -> println("mers")
    EnumCar.BMW -> println("mers")
}

fun selectShape(shape: Shape): Double = when (shape) {
    is Shape.Nothing -> 0.0
    is Shape.Circle -> shape.radius
    is Shape.Rectangle -> shape.height * shape.width
}

enum class EnumCar() {
    BMW(),
    MERS(),
    AUDI()
}

sealed class Shape {
    object Nothing : Shape()
    class Circle(val radius: Double) : Shape()
    data class Rectangle(val width: Double, val height: Double) : Shape()
}
