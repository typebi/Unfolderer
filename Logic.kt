import java.io.File
import javax.swing.JTextArea

class Logic(private val textArea: JTextArea) {
    fun execute(path :  String){
        val rootDir = File(path)
        if(!rootDir.exists()) return
        handleDir(rootDir)
    }
    private fun handleDir(dir : File){
        val files = dir.listFiles()
        if (files!=null){
            when (files.size){
                0 -> textArea.append(dir.name + " : Empty Folder 파일 없음"+"\n")
                else -> walkDir(dir)
            }
        }else{
            textArea.append("handleDir dir.listFiles() is null"+"\n")
        }
    }
    private fun walkDir(dir : File){
        var isNotContainDir = true
        val files = dir.listFiles()
        if (files!=null){
            files.forEach {
                if(it.isDirectory) {
                    handleDir(it)
                    isNotContainDir = false
                }
            }
            if(isNotContainDir){
                if(files.size==1) handleFile(dir, files[0])
                else handleFiles(dir, files)
            }
        }else{
            textArea.append("walkDir dir.listFiles() is null"+"\n")
        }
    }
    private fun handleFile(dir : File , f : File){
        textArea.append(dir.path+"."+f.extension+"\n")
        val newFile = File(dir.path+"."+f.extension)
        val result = f.renameTo(newFile)
        if (result)
            textArea.append("Complete\n")
        else
            textArea.append("Failure\n")
    }
    private fun handleFiles(dir : File , files : Array<File>){
        var idx = 1
        files.forEach {
            val newFile = File(dir.path+" $idx."+it.extension)
            textArea.append(dir.path+"."+it.extension+"\n")
            val result = it.renameTo(newFile)
            if (result)
                textArea.append("Complete\n")
            else
                textArea.append("Failure\n")
            idx++
        }
    }
}