import java.awt.event.ActionListener
import javax.swing.*

@Suppress("SpellCheckingInspection")
class MainView {
    private val frame by lazy { JFrame("Unfolderer") }
    fun makeGui(){
        setDefault()
        setComps()
        frame.repaint()
    }
    private fun setDefault(){
        frame.apply {
            setSize(600,250)
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            isVisible = true
            setLocation(600,400)
            layout = null
        }
    }
    private fun setComps(){
        val textFieldName = JLabel("PATH").apply {
            setBounds(20,10,50,25)
        }
        val textField = JTextField().apply {
            setBounds(60,10,500,30)
        }
        val runButton = JButton("Execute").apply {
            setBounds(250,50,100,25)
        }
        val textAreaName = JLabel("LOG").apply {
            setBounds(20,100,50,25)
        }
        val textArea = JTextArea().apply {
            isEditable = false
        }
        val scrollPane = JScrollPane(textArea).apply {
            setBounds(60,100,500,100)
        }

        textField.addActionListener(ActionListener {
            val path = textField.text
            val result = JOptionPane.showConfirmDialog(null, "\"$path\"", "Confirm", JOptionPane.OK_CANCEL_OPTION)
            checkResult(result, path, textArea)
        })
        runButton.addActionListener(ActionListener {
            val path = textField.text
            val result = JOptionPane.showConfirmDialog(null, "\"$path\"", "Confirm", JOptionPane.OK_CANCEL_OPTION)
            checkResult(result, path, textArea)
        })
        frame.add(textFieldName)
        frame.add(textField)
        frame.add(runButton)
        frame.add(textAreaName)
        frame.add(scrollPane)
    }
    private fun checkResult(result : Int, path : String, textArea : JTextArea){
        when(result){
            JOptionPane.YES_OPTION -> Logic(textArea).execute(path)
        }
    }
}
fun main(args: Array<String>) {
    MainView().makeGui()
}