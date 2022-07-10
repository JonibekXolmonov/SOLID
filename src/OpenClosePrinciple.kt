fun main() {
    val movies = listOf(
        Movie("Movie1", Type.COMEDY, Rating.GOOD, Auditory.ADULT),
        Movie("Movie2", Type.COMEDY, Rating.BAD, Auditory.CHILDREN),
        Movie("Movie3", Type.HISTORICAL, Rating.BAD, Auditory.ADULT),
        Movie("Movie4", Type.HISTORICAL, Rating.EXCELLENT, Auditory.CHILDREN),
        Movie("Movie5", Type.ACTION_MOVIE, Rating.EXCELLENT, Auditory.ADULT),
        Movie("Movie6", Type.ACTION_MOVIE, Rating.GOOD, Auditory.CHILDREN),
        Movie("Movie7", Type.COMEDY, Rating.EXCELLENT, Auditory.YOUNGSTER),
        Movie("Movie8", Type.SOAP_OPERA, Rating.EXCELLENT, Auditory.CHILDREN),
        Movie("Movie9", Type.SOAP_OPERA, Rating.GOOD, Auditory.CHILDREN),
        Movie("Movie10", Type.SCIENCE_FICTION, Rating.EXCELLENT, Auditory.YOUNGSTER),
        Movie("Movie11", Type.SCIENCE_FICTION, Rating.GOOD, Auditory.YOUNGSTER),
        Movie("Movie12", Type.SCIENCE_FICTION, Rating.GOOD, Auditory.YOUNGSTER),
        Movie("Movie13", Type.SCIENCE_FICTION, Rating.BAD, Auditory.CHILDREN),
    )

    val filter = OCPFilter()
    println(filter.filter(movies, TypeSpecification(Type.ACTION_MOVIE)))
    println(filter.filter(movies, TypeSpecification(Type.SOAP_OPERA)))
    println(filter.filter(movies, RatingSpecification(Rating.EXCELLENT)))
    println(filter.filter(movies, RatingSpecification(Rating.BAD)))


    println(
        filter.filter(
            movies,
            TwoSpecification(TypeSpecification(Type.SCIENCE_FICTION), RatingSpecification(Rating.GOOD))
        )
    )
    println(
        filter.filter(
            movies,
            TwoSpecification(AuditorySpecification(Auditory.CHILDREN), TypeSpecification(Type.ACTION_MOVIE))
        )
    )
}

enum class Rating {
    BAD, GOOD, EXCELLENT
}

enum class Type {
    COMEDY, HISTORICAL, SCIENCE_FICTION, ACTION_MOVIE, SOAP_OPERA
}

enum class Auditory {
    CHILDREN, YOUNGSTER, ADULT
}


data class Movie(val name: String, val type: Type, val rating: Rating, val auditory: Auditory)

interface Specification<in T> {
    fun isSatisfied(item: T): Boolean
}

interface Filter<T> {
    fun filter(items: List<T>, specification: Specification<T>): List<T>
}

class RatingSpecification(private val rating: Rating) : Specification<Movie> {

    override fun isSatisfied(item: Movie): Boolean = rating == item.rating

}

class TypeSpecification(private val type: Type) : Specification<Movie> {

    override fun isSatisfied(item: Movie): Boolean = type == item.type

}

class AuditorySpecification(private val auditory: Auditory) : Specification<Movie> {

    override fun isSatisfied(item: Movie): Boolean = auditory == item.auditory

}

class TwoSpecification(private val first: Specification<Movie>, private val second: Specification<Movie>) :
    Specification<Movie> {

    override fun isSatisfied(item: Movie): Boolean = first.isSatisfied(item) && second.isSatisfied(item)

}

class OCPFilter : Filter<Movie> {

    override fun filter(items: List<Movie>, specification: Specification<Movie>): List<Movie> =
        items.filter { item -> specification.isSatisfied(item) }

}