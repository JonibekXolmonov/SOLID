fun main() {
    val cars = listOf(
        Car("Car1", Brand.BMW, Price.MEDIUM, Color.BLACK),
        Car("Car2", Brand.BMW, Price.CHEAP, Color.BLACK),
        Car("Car3", Brand.CHEVROLET, Price.CHEAP, Color.WHITE),
        Car("Car4", Brand.CHEVROLET, Price.CHEAP, Color.WHITE),
        Car("Car5", Brand.CHEVROLET, Price.MEDIUM, Color.WHITE),
        Car("Car6", Brand.BMW, Price.MEDIUM, Color.WHITE),
        Car("Car7", Brand.TESLA, Price.EXPENSIVE, Color.WHITE),
        Car("Car8", Brand.TESLA, Price.EXPENSIVE, Color.WHITE),
        Car("Car9", Brand.TESLA, Price.EXPENSIVE, Color.BLACK),
        Car("Car10", Brand.TESLA, Price.MEDIUM, Color.BLACK),
        Car("Car11", Brand.BMW, Price.MEDIUM, Color.BLACK),
        Car("Car12", Brand.BMW, Price.CHEAP, Color.BLACK),
        Car("Car13", Brand.CHEVROLET, Price.CHEAP, Color.GREY),
        Car("Car14", Brand.CHEVROLET, Price.MEDIUM, Color.GREY),
        Car("Car15", Brand.CHEVROLET, Price.MEDIUM, Color.GREY),
        Car("Car16", Brand.BMW, Price.EXPENSIVE, Color.GREY),
        Car("Car17", Brand.TESLA, Price.MEDIUM, Color.GREY)
    )

    val filterCarOptimal = FilterCarOptimal()
    println(filterCarOptimal.filter(cars, FilterByColor(Color.GREY)))

    println(filterCarOptimal.filter(cars, FilterByTwo(FilterByColor(Color.BLACK), FilterByBrand(Brand.CHEVROLET))))

    println(
        filterCarOptimal.filter(
            cars,
            FilterByThree(FilterByColor(Color.WHITE), FilterByBrand(Brand.TESLA), FilterByPrice(Price.EXPENSIVE))
        )
    )
}

data class Car(val name: String, val brand: Brand, val price: Price, val color: Color)

enum class Brand {
    BMW, CHEVROLET, TESLA
}

enum class Price {
    CHEAP, MEDIUM, EXPENSIVE
}

enum class Color {
    WHITE, BLACK, GREY
}

//open close breaking code
class FilterCar {
    fun filterByBrand(list: List<Car>, brand: Brand): List<Car> =
        list.filter { car -> car.brand == brand }

    fun filterByPrice(list: List<Car>, price: Price): List<Car> =
        list.filter { car -> car.price == price }

    fun filterByColorAndPrice(list: List<Car>, price: Price, color: Color) {
        list.filter { car -> car.color == color && car.price == price }
    }
}
//30 (2^30-1)*3

//code going for open close
interface Satisfaction<in T> {
    fun isSatisfied(item: T): Boolean
}

class FilterByPrice(private val price: Price) : Satisfaction<Car> {
    override fun isSatisfied(item: Car): Boolean = item.price == price
}

class FilterByBrand(private val brand: Brand) : Satisfaction<Car> {
    override fun isSatisfied(item: Car): Boolean = item.brand == brand
}

class FilterByColor(private val color: Color) : Satisfaction<Car> {
    override fun isSatisfied(item: Car): Boolean = item.color == color
}

class FilterByTwo(val first: Satisfaction<Car>, val second: Satisfaction<Car>) : Satisfaction<Car> {
    override fun isSatisfied(item: Car): Boolean = first.isSatisfied(item) && second.isSatisfied(item)
}

class FilterByThree(val first: Satisfaction<Car>, val second: Satisfaction<Car>, val third: Satisfaction<Car>) :
    Satisfaction<Car> {
    override fun isSatisfied(item: Car): Boolean =
        first.isSatisfied(item) && second.isSatisfied(item) && third.isSatisfied(item)
}

class FilterCarOptimal : FilterCars<Car> {
    override fun filter(items: List<Car>, satisfaction: Satisfaction<Car>): List<Car> =
        items.filter { car -> satisfaction.isSatisfied(car) }
}

interface FilterCars<T> {
    fun filter(items: List<T>, satisfaction: Satisfaction<T>): List<T>
}


