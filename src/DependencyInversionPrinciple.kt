fun main() {
    val parent = Person("Ali")
    val child1 = Person("Husan")
    val child2 = Person("Hasan")

    val relationShips = RelationShips()
    relationShips.addParentToChild(parent, child1)
    relationShips.addParentToChild(parent, child2)

    val research = Research(relationShips, "Ali")
}

data class Person(val name: String)

enum class RelationShip {
    PARENT, CHILD
}

class Triple<A, B, C>(val a: A, val b: B, val c: C)

//opposite
/*
//quyi daraja moduli
class RelationShips {
    private val relations: ArrayList<Triple<Person, RelationShip, Person>> = ArrayList()

    fun getRelations(): List<Triple<Person, RelationShip, Person>> {
        return relations
    }

    fun addParentToChild(parent: Person, child: Person) {
        this.relations.add(Triple(parent, RelationShip.PARENT, child))
        this.relations.add(Triple(child, RelationShip.CHILD, parent))
    }
}

//Yuqori daraja moduli
class Research(val relationShips: RelationShips, val name: String) {
    init {
        val relations = relationShips.getRelations()
        relations.filter { x -> x.a.name == name && x.b == RelationShip.PARENT }.forEach {
            println(name + " has a child called" + " " + it.c.name)
        }
    }
}
*/
//for
interface RelationShipsBrowse {
    fun findAllChildren(name: String): List<Person>
}

class RelationShips : RelationShipsBrowse {
    private val relations: ArrayList<Triple<Person, RelationShip, Person>> = ArrayList()

    fun getRelations(): List<Triple<Person, RelationShip, Person>> {
        return relations
    }

    fun addParentToChild(parent: Person, child: Person) {
        this.relations.add(Triple(parent, RelationShip.PARENT, child))
        this.relations.add(Triple(child, RelationShip.CHILD, parent))
    }

    override fun findAllChildren(name: String): List<Person> {
        return relations.filter { x -> x.a.name == name && x.b == RelationShip.PARENT }
            .map(Triple<Person, RelationShip, Person>::c)
    }
}

//Yuqori daraja moduli
class Research(relationShipsBrowse: RelationShipsBrowse, name: String) {
    init {
        val children = relationShipsBrowse.findAllChildren(name)
        children.forEach {
            println(name + " has a child " + it.name)
        }
    }
}