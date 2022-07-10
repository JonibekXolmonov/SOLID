import java.io.File

interface ScanFile {
    fun scan(file: File)
}

interface PrintFile {
    fun print(file: File)
}

interface FaxFile {
    fun fax(file: File, phoneNumber: String)
}

interface ScanAndPrint : ScanFile, PrintFile

interface ModernPrintImp : ScanFile, PrintFile, FaxFile

class CanonBobosi : ScanAndPrint {
    override fun scan(file: File) {

    }

    override fun print(file: File) {

    }
}

class CanonLatestVersion : ModernPrintImp {
    override fun scan(file: File) {

    }

    override fun print(file: File) {

    }

    override fun fax(file: File, phoneNumber: String) {

    }
}
