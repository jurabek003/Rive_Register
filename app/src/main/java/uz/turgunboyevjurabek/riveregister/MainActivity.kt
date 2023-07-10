package uz.turgunboyevjurabek.riveregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import app.rive.runtime.kotlin.core.Rive
import uz.turgunboyevjurabek.riveregister.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val stateMachineName="State Machine 1"
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Rive.init(this)
        // Name EditTexti uchun
        binding.edtName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.loginScanner.controller.setBooleanState(stateMachineName, "Check", true)
            }else{
                binding.loginScanner.controller.setBooleanState(stateMachineName, "Check", false)
            }
        }
        // Password EditTexti uchun
        binding.edtPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.loginScanner.controller.setBooleanState(stateMachineName, "hands_up", true)
            }else{
                binding.loginScanner.controller.setBooleanState(stateMachineName, "hands_up", false)
            }
        }


        binding.edtName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    binding.loginScanner.controller.setNumberState(stateMachineName, "Look", s!!.length.toFloat())
                }catch (e:Exception){
                }
            }
        })
        binding.btnOk.setOnClickListener {

            if (binding.edtName.text!!.isNotEmpty() && binding.edtName.text.toString()=="Jurabek"
                && binding.edtPassword.text!!.isNotEmpty() && binding.edtPassword.text.toString()=="allambalo"
            ){
                binding.loginScanner.controller.fireState(stateMachineName,"success")
                binding.edtName.clearFocus()
                binding.edtPassword.clearFocus()

            }else{
                binding.loginScanner.controller.fireState(stateMachineName,"fail")
                binding.edtName.clearFocus()
                binding.edtPassword.clearFocus()

            }
        }
    }
}