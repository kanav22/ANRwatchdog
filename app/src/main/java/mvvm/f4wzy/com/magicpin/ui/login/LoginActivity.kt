package mvvm.f4wzy.com.magicpin.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.anrwatchdog.ANRWatchDog
import mvvm.f4wzy.com.magicpin.R
import mvvm.f4wzy.com.magicpin.databinding.ActivityLoginBinding
import mvvm.f4wzy.com.magicpin.ui.login.viewmodel.LoginViewModel
import mvvm.f4wzy.com.magicpin.util.CustomeProgressDialog

class LoginActivity : AppCompatActivity() {

    var binding: ActivityLoginBinding? = null
    var viewmodel: LoginViewModel? = null
    var customeProgressDialog: CustomeProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding?.viewmodel = viewmodel
        customeProgressDialog = CustomeProgressDialog(this)
        //added ANR watchdog for ANR monitoring
        ANRWatchDog().start()
        initObservables()


    }

    private fun initObservables() {
        viewmodel?.progressDialog?.observe(this, Observer {
            if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
        })

        viewmodel?.userLogin?.observe(this, Observer { user ->
            Toast.makeText(this, "welcome, ${user?.last_name}", Toast.LENGTH_LONG).show()
        })
    }


}
