import java.io.File

fun main() {

}

data class Document(val document: File)

interface Print {
    fun print(document: Document)
}

interface Scan {
    fun scan(document: Document)
}

interface Fax {
    fun print(document: Document)
}

private interface PrintAndScan : Print, Scan

class Canon1 : Scan {
    override fun scan(document: Document) {
        //scanning
    }
}

class ModernCanon : PrintAndScan {
    override fun print(document: Document) {
        //printing
    }

    override fun scan(document: Document) {
        //scanning
    }
}